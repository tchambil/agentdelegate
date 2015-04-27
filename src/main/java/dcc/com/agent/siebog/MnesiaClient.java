package dcc.com.agent.siebog;

/**
 * Created by teo on 27/04/15.
 */

import com.ericsson.otp.erlang.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Erlang related imports
 */
/**
 * Java/Erlang interface related imports
 */

    public class MnesiaClient {

    private OtpNode node = null;
    private OtpMbox mBox = null;
    private String user = "maria@artemis";
    private String server = "mnesiadb_server";

    /**
     * Initialize any state for this DB.
     * Called once per DB instance; there is one DB instance per client thread.
     */
    public void init() throws IOException {
        try {
            node = new OtpNode("192.168.50.19");
            mBox = node.createMbox();
            OtpErlangTuple msg =
                    new OtpErlangTuple(
                            new OtpErlangObject[] {
                                    mBox.self(),
                                    new OtpErlangAtom("init")
                            });
            mBox.send(server, user, msg);
            OtpErlangAtom recAtom = (OtpErlangAtom) mBox.receive();
            OtpErlangAtom okAtom = new OtpErlangAtom("ok");
            assert recAtom.equals(okAtom);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Cleanup any state for this DB.
     * Called once per DB instance; there is one DB instance per client thread.
     */
    public void cleanup() throws IOException {
        return;
    }

    /**
     * Read a record from the database. Each field/value pair from the result
     * will be stored in a HashMap.
     *
     * @param table The name of the table
     * @param key The record key of the record to read.
     * @param fields The list of fields to read, or null for all of them
     * @param result A HashMap of field/value pairs for the result
     * @return Zero on success, a non-zero error code on error
     */
    public int read(String table, String key, Set<String> fields,
                    HashMap<String,String> result) {

        boolean returnAllFields = fields == null;

        try {
            OtpErlangAtom tableAtom = new OtpErlangAtom(table);
            OtpErlangString keyStr = new OtpErlangString(key);
            OtpErlangTuple msg =
                    new OtpErlangTuple(
                            new OtpErlangObject[] {
                                    mBox.self(),
                                    new OtpErlangAtom("read"),
                                    tableAtom,
                                    keyStr
                            });
            mBox.send(server, user, msg);
            OtpErlangObject received = mBox.receive();
            OtpErlangAtom noneAtom = new OtpErlangAtom("none");
            if (received.equals(noneAtom))
                return 1;
            else {
                if (!returnAllFields) {
                    Iterator<String> itr = fields.iterator();
                    while (itr.hasNext()) {
                        int i = Integer.parseInt(itr.next().substring(5));
                        String value =
                                ((OtpErlangTuple) received).elementAt(i + 2).toString();
                        result.put(new String("field" + i), new String(value));
                    }
                }
                else {
                    int recArity = ((OtpErlangTuple) received).arity();
                    for (int i = 0; i < recArity - 2; i++) {
                        String value =
                                ((OtpErlangTuple) received).elementAt(i + 2).toString();
                        result.put(new String("field" + i), new String(value));
                    }
                }
            }
            return 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }

    /**
     * Perform a range scan for a set of records in the database. Each
     * field/value pair from the result will be stored in a HashMap.
     *
     * @param table The name of the table
     * @param startkey The record key of the first record to read.
     * @param recordcount The number of records to read
     * @param fields The list of fields to read, or null for all of them
     * @param result A Vector of HashMaps, where each HashMap is a set field/value pairs for one record
     * @return Zero on success, a non-zero error code on error
     */
    public int scan(String table, String startkey, int recordcount,
                    Set<String> fields, Vector<HashMap<String,String>> result) {

        boolean returnAllFields = fields == null;

        try {
            OtpErlangAtom tableAtom = new OtpErlangAtom(table);
            OtpErlangString keyStr = new OtpErlangString(startkey);
            OtpErlangInt recCountInt = new OtpErlangInt(recordcount);
            OtpErlangTuple msg =
                    new OtpErlangTuple(
                            new OtpErlangObject[] {
                                    mBox.self(),
                                    new OtpErlangAtom("scan"),
                                    tableAtom,
                                    keyStr,
                                    recCountInt
                            });
            mBox.send(server, user, msg);
            OtpErlangList received = (OtpErlangList) mBox.receive();
            OtpErlangAtom noneAtom = new OtpErlangAtom("none");
            Iterator<OtpErlangObject> itr1 = received.iterator();
            while (itr1.hasNext()) {
                OtpErlangObject recElem = itr1.next();
                if (recElem.equals(noneAtom))
                    return 1;
                else {
                    HashMap<String,String> tuple = new HashMap<String, String>();
                    if (!returnAllFields) {
                        Iterator<String> itr2 = fields.iterator();
                        while (itr2.hasNext()) {
                            int i = Integer.parseInt(itr2.next().substring(5));
                            String value =
                                    ((OtpErlangTuple) recElem).elementAt(i + 2).toString();
                            tuple.put(new String("field" + i), new String(value));
                        }
                    }
                    else {
                        int recArity = ((OtpErlangTuple) recElem).arity();
                        for (int i = 0; i < recArity - 2; i++) {
                            String value =
                                    ((OtpErlangTuple) recElem).elementAt(i + 2).toString();
                            tuple.put(new String("field" + i), new String(value));
                        }
                    }
                    result.add(tuple);
                }
            }
            return 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }

    /**
     * Update a record in the database. Any field/value pairs in the specified
     * values HashMap will be written into the record with the specified record
     * key, overwriting any existing values with the same field name.
     *
     * @param table The name of the table
     * @param key The record key of the record to write.
     * @param values A HashMap of field/value pairs to update in the record
     * @return Zero on success, a non-zero error code on error
     */
    public int update(String table, String key, HashMap<String,String> values) {
        try {
            OtpErlangAtom tableAtom = new OtpErlangAtom(table);
            OtpErlangString keyStr = new OtpErlangString(key);
            OtpErlangObject fields[] = new OtpErlangObject[values.size()];
            int i = 0;
            for (String field : values.keySet()) {
                int f = Integer.parseInt(field.substring(5)) + 3;
                String value = values.get(field);
                OtpErlangTuple tuple =
                        new OtpErlangTuple(
                                new OtpErlangObject[] {
                                        new OtpErlangInt(f),
                                        new OtpErlangString(value)
                                });
                fields[i++] = tuple;
            }
            OtpErlangTuple msg =
                    new OtpErlangTuple(
                            new OtpErlangObject[] {
                                    mBox.self(),
                                    new OtpErlangAtom("update"),
                                    tableAtom,
                                    keyStr,
                                    new OtpErlangList(fields)
                            });
            mBox.send(server, user, msg);
            OtpErlangAtom recAtom = (OtpErlangAtom) mBox.receive();
            OtpErlangAtom okAtom = new OtpErlangAtom("ok");
            assert recAtom.equals(okAtom);
            return 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }

    /**
     * Insert a record in the database. Any field/value pairs in the
     * specified values HashMap will be written into the record with the
     * specified record key.
     *
     * @param table The name of the table
     * @param key The record key of the record to insert.
     * @param values A HashMap of field/value pairs to insert in the record
     * @return Zero on success, a non-zero error code on error
     */
    public int insert(String table, String key, HashMap<String,String> values) {
        try {
            OtpErlangAtom tableAtom = new OtpErlangAtom(table);
            OtpErlangString keyStr = new OtpErlangString(key);
            OtpErlangObject fields[] = new OtpErlangObject[values.size()];
            int i = 0;
            for (String field : values.keySet()) {
                int f = Integer.parseInt(field.substring(5)) + 3;
                String value = values.get(field);
                OtpErlangTuple tuple =
                        new OtpErlangTuple(
                                new OtpErlangObject[] {
                                        new OtpErlangInt(f),
                                        new OtpErlangString(value)
                                });
                fields[i++] = tuple;
            }
            OtpErlangTuple msg =
                    new OtpErlangTuple(
                            new OtpErlangObject[] {
                                    mBox.self(),
                                    new OtpErlangAtom("insert"),
                                    tableAtom,
                                    keyStr,
                                    new OtpErlangList(fields)
                            });
            mBox.send(server, user, msg);
            OtpErlangAtom recAtom = (OtpErlangAtom) mBox.receive();
            OtpErlangAtom okAtom = new OtpErlangAtom("ok");
            assert recAtom.equals(okAtom);
            return 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }

    /**
     * Delete a record from the database.
     *
     * @param table The name of the table
     * @param key The record key of the record to delete.
     * @return Zero on success, a non-zero error code on error
     */
    public int delete(String table, String key) {
        try {
            OtpErlangAtom tableAtom = new OtpErlangAtom(table);
            OtpErlangString keyStr = new OtpErlangString(key);
            OtpErlangTuple msg =
                    new OtpErlangTuple(
                            new OtpErlangObject[] {
                                    mBox.self(),
                                    new OtpErlangAtom("delete"),
                                    tableAtom,
                                    keyStr
                            });
            mBox.send(server, user, msg);
            OtpErlangAtom recAtom = (OtpErlangAtom) mBox.receive();
            OtpErlangAtom okAtom = new OtpErlangAtom("ok");
            assert recAtom.equals(okAtom);
            return 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }
}
