package dcc.com.agent.siebog;

/**
 * Created by teo on 27/04/15.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
/**
 * Agent identifier, consists of the runtime name and the platform identifier, in the form of
 * "name@host".
 *
 * @author <a href="tntvteod@neobee.net">Teodor-Najdan Trifunov</a>
 * @author <a href="mitrovic.dejan@gmail.com">Dejan Mitrovic</a>
 */
public final class AID implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String host;
    private final String str; // string representation

    private static final String HOST_NAME = "xjaf"; // TODO Get cluster/host name.
    public static final AID EXTERNAL_CLIENT = new AID("", "");

    public AID() {
        name = "";
        host = "";
        str = "";
    }



    public AID(String name, String host) {
        this.name = name;
        this.host = host;
        str = name + "@" + host;
    }

    public AID(String jsonStr) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            name = json.getString("name");
            host = json.has("host") ? json.getString("host") : HOST_NAME;
            str = name + "@" + host;

        } catch (JSONException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public int hashCode() {
        return str.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AID other = (AID) obj;
        return str.equals(other.str);
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", name);
            obj.put("host", host);
            obj.put("str", str);
        } catch (JSONException ex) {
        }
        return obj.toString();
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }



    public String getStr() {
        return str;
    }
}

