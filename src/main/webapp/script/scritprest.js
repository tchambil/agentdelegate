Ext.onReady(function() {
    Ext.define('org.techzoo.LoginForm', {
        extend : 'Ext.form.Panel',
        bodyPadding : 10,
        width : 280,
        frame : true,
        title : 'User Authentication',
        items : [ {
            xtype : 'textfield',
            name : 'name',
            allowBlank : false,
            msgTarget : 'side',
            fieldLabel : '<span class="fabold">Name</span>'
        }, {
            xtype : 'textfield',
            name : 'pwd',
            allowBlank : false,
            msgTarget : 'side',
            minLength : 6,
            fieldLabel : '<span class="fabold">Password</span>',
            inputType : 'password'
        }],
        dockedItems : [{
            xtype : 'toolbar',
            padding : '2 0 2 0',
            dock : 'bottom',
            ui : 'footer',
            items : [{
                xtype : 'tbfill'
            },{
                text : 'Authenticate User',
                listeners : {
                    click : function(btn) {
                        var frm = btn.up('form');
                        if (frm.getForm().isValid()) {
                            Ext.Ajax.request({
                                url 	: '/SpringXMLFree/userAuthenticate',
                                method	: 'POST',
                                jsonData: frm.getForm().getValues(),
                                success : function(resp) {
                                    var response = Ext.decode(resp.responseText);
                                    if(response != null && response.success)
                                    {
                                        Ext.Msg.show({
                                            title:'User Authentication',
                                            msg	: response.message,
                                            icon: Ext.window.MessageBox.INFO,
                                            buttons: Ext.Msg.OK
                                        });
                                    }
                                    else if(response != null && !response.success)
                                    {
                                        Ext.Msg.show({
                                            title:'User Authentication',
                                            msg	: response.message,
                                            icon: Ext.window.MessageBox.ERROR,
                                            buttons: Ext.Msg.OK
                                        });
                                    }
                                }
                            });
                        }
                    }//click
                }
            }]
        }],
    });

    Ext.create('org.techzoo.LoginForm', {renderTo : 'output'});
});
