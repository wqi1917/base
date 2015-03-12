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

/**
 * 用户注册接口测试类
 * 
 * @author wangq
 * @version $Id: TUserManage.java, v 0.1 2014-11-19 上午11:07:50 wangq Exp $
 */
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class TAccountManage {

    public ClientConfig cc      = null;
    public Client       client  = null;

    public String       url     = "http://localhost/msggw/server/account";
    public String       xmppUrl = "http://218.205.81.27:9090/plugins/openservice/user";

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

        userManageAdd();
        userManageSel();
        userManageUpdate();
        userManageSel();
        userManageDel();

        //        userOpenfire();

    }

    private void userManageAdd() {
        WebResource wr = client.resource(url + "/manageform");

        String xml = "XMP_12344:2435:test1;XMP_142344:2436:test2;XMP_1423442:2433:test3";

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("type", "add");
        form.add("userinfos", xml);

        //        ClientResponse response = wr
        //            .header(HttpHeaders.CONTENT_TYPE, SMsgConstants.CONTENT_TYPE_XML).entity(xml)
        //            .post(ClientResponse.class);

        ClientResponse response = wr.post(ClientResponse.class, form);

    }

    private void userManageDel() {
        WebResource wr = client.resource(url + "/manageform");

        String xml = "XMP_12344:2435:test1;XMP_142344:2436:test2;XMP_1423442:2433:test3";

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("type", "delete");
        form.add("userinfos", xml);

        //        ClientResponse response = wr
        //            .header(HttpHeaders.CONTENT_TYPE, SMsgConstants.CONTENT_TYPE_XML).entity(xml)
        //            .post(ClientResponse.class);

        ClientResponse response = wr.post(ClientResponse.class, form);

    }

    private void userManageUpdate() {
        WebResource wr = client.resource(url + "/manageform");

        String xml = "XMP_12344:xxxxx:test1-111;XMP_142344:yyyyyy:test2--222;XMP_1423442:zzzz:test3---3";

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("type", "update");
        form.add("userinfos", xml);

        //        ClientResponse response = wr
        //            .header(HttpHeaders.CONTENT_TYPE, SMsgConstants.CONTENT_TYPE_XML).entity(xml)
        //            .post(ClientResponse.class);

        ClientResponse response = wr.post(ClientResponse.class, form);

    }

    private void userManageSel() {
        WebResource wr = client.resource(url + "/manageform");

        String xml = "XMP_12344;XMP_142344:2436:test2;XMP_1423442:2433:test3";

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("type", "search");
        form.add("userinfos", xml);

        //        ClientResponse response = wr
        //            .header(HttpHeaders.CONTENT_TYPE, SMsgConstants.CONTENT_TYPE_XML).entity(xml)
        //            .post(ClientResponse.class);

        ClientResponse response = wr.post(ClientResponse.class, form);

    }

    /**
     * 增加用户测试类
     */
    private void userOpenfire() {
        System.out.println("Start userManageAdd");
        WebResource wr = client.resource(xmppUrl + "?type=add");

        String xml = "XMpp_12344:243:test";

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("type", "add");
        form.add("userinfos", xml);

        ClientResponse response = wr
            .header(HttpHeaders.CONTENT_TYPE, CommonConstants.CONTENT_TYPE_XML).entity(xml)
            .post(ClientResponse.class);

        //        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("sendSendText:" + response);
        System.out.println("sendSendEntity:" + response.getEntity(String.class));
    }

}
