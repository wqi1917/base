package test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hms.util.CommonConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TMsgSend {

    public ClientConfig cc      = null;
    public Client       client  = null;

    public String       url     = "http://localhost/msggw/server/sendmsg";
    //    public String       url     = "http://218.205.81.33/msggw/server/sendmsg";
    //    public String       url     = "http://localhost/padata/open/sendmsg";
    //    public String       url     = "http://10.2.48.149/padata/client/msg";
    //    public String       sip_uri = "sip:0086138123456789@rcs.chinamobile.com";
    public String       xmppUrl = "http://218.205.81.27:9090/plugins/pubacct/msg/msgsubmit";

    //        public String       revsend = "http://218.205.81.40/padata/server/msg/recvmsg";
    public String       revsend = "http://localhost/msggw/server/msg/recvmsg";

    public String       sip_uri = "sip:+8619001230212@huawei.com";

    @Before
    public void prepare() {
        cc = new DefaultClientConfig();
        client = Client.create(cc);
    }

    @After
    public void clear() {
    }

    @Test
    public void testAll() {

        sendSendXmppText();
        //        revsend();

    }

    @SuppressWarnings("unchecked")
    private void sendSendXmppText() {
        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);
        WebResource wr = client.resource(url + "/sendtextmsg");

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("msgbiztype", "TASKGROUP_NOTIFY_PUSH");
        form.add("msgid", "yui2yut01qwer408884ryu231600");
        form.add("msgdigest", "信息摘要");
        form.add("receivers", "+8615912341011_3388665511");
        form.add("msgtext", "测试消息你好是你吗？");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("sendSendText:" + response);
        System.out.println("sendSendEntity:" + response.getEntity(String.class));
    }

    @SuppressWarnings("unchecked")
    private void revsend() {

        System.out.println("Start revsend");
        WebResource wr = client.resource(revsend);

        String xml = "<?xml version='1.0' encoding='utf-8'?>"
                     + "<body>"
                     + "<msgbiztype>PADATA_GROUP_MSG</msgbiztype>"
                     + "<msg_id>PADATA_GROUP_MSG</msg_id>"
                     + "<sender>+8619001230400@li726-26/Smack</sender>"
                     + "<msg_content><media_type>10</media_type><create_time>2014-10-11 11:01:07</create_time><text>{\"sdfd\":\"sdfsdf\"}</text><pa_uuid>539a57cbe4b051aad105bf85</pa_uuid></msg_content>"
                     + "</body>";
        ClientResponse response = wr
            .header(HttpHeaders.CONTENT_TYPE, CommonConstants.CONTENT_TYPE_XML).entity(xml)
            .post(ClientResponse.class);

        System.out.println("sendSendText:" + response);
        System.out.println("sendSendEntity:" + response.getEntity(String.class));
    }
}
