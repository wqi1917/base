package test;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 消息业务类型管理测试类
 * 
 * @author yanglei
 * @version $Id: TConfigInfoManage.java, v 0.1 2014年11月20日 下午4:42:20 yanglei Exp $
 */
public class TConfigInfoManage {

    public ClientConfig cc     = null;
    public Client       client = null;

    public String       url    = "http://localhost/msggw/server/configinfo";

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
        addTest();
        //deleteByConfig_uuidTest();
        //deleteByBusiness_platTest();
        //updateTest();
        //selectByConfig_uuidTest();
        //selectByMsgbiztypeTest();
        //        selectByBusiness_platTest();
    }

    @SuppressWarnings("unchecked")
    private void addTest() {

        System.out.println("Start addTest");
        WebResource wr = client.resource(url + "/add");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("direction", "0");
        form.add("msgbiztype", "mswerwerweg333331");
        form.add("msgbiztype_name", "name");
        form.add("business_plat", "plat");
        form.add("upmsg_service", "upmsg");
        form.add("downmsg_service", "downmsg");
        form.add("callback_service", "callback");
        form.add("max_receiver_num", "10");//只能是String类型，String.valueof(10)

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("addTest:" + response);
        System.out.println("addTestEntity:" + response.getEntity(String.class));

    }

    @SuppressWarnings("unchecked")
    private void deleteByConfig_uuidTest() {
        System.out.println("Start deleteByConfig_uuidTest");
        WebResource wr = client.resource(url + "/deletebyconfig_uuid");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("config_uuid", "4");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("deleteByConfig_uuidTest:" + response);
        System.out.println("deleteByConfig_uuidTestEntity:" + response.getEntity(String.class));

    }

    @SuppressWarnings("unchecked")
    private void deleteByBusiness_platTest() {
        System.out.println("Start deleteByBusiness_platTest");
        WebResource wr = client.resource(url + "/deletebybusiness_plat");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("business_plat", "plat1");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("deleteByBusiness_platTest:" + response);
        System.out.println("deleteByBusiness_platTestEntity:" + response.getEntity(String.class));

    }

    @SuppressWarnings("unchecked")
    private void updateTest() {
        System.out.println("Start updateTest");
        WebResource wr = client.resource(url + "/update");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("config_uuid", "7");
        form.add("direction", "0");
        form.add("msgbiztype", "msg2");
        form.add("msgbiztype_name", "name");
        form.add("business_plat", "plat");
        form.add("upmsg_service", "upmsg");
        form.add("downmsg_service", "downmsg");
        form.add("callback_service", "callback");
        form.add("max_receiver_num", "10");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("updateTest:" + response);
        System.out.println("updateTestEntity:" + response.getEntity(String.class));

    }

    @SuppressWarnings("unchecked")
    private void selectByConfig_uuidTest() {
        System.out.println("Start selectByConfig_uuidTest");
        WebResource wr = client.resource(url + "/selectbyconfig_uuid");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("config_uuid", "7");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("selectByConfig_uuidTest:" + response);
        System.out.println("selectByConfig_uuidTestEntity:" + response.getEntity(String.class));
    }

    @SuppressWarnings("unchecked")
    private void selectByMsgbiztypeTest() {
        System.out.println("Start selectByMsgbiztypeTest");
        WebResource wr = client.resource(url + "/selectbymsgbiztype");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("msgbiztype", "msg");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("selectByMsgbiztypeTest:" + response);
        System.out.println("selectByMsgbiztypeTestEntity:" + response.getEntity(String.class));
    }

    @SuppressWarnings("unchecked")
    public void selectByBusiness_platTest() {

        System.out.println("Start selectByBusiness_platTest");
        WebResource wr = client.resource(url + "/selectbybusiness_plat");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("business_plat", "plat");

        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("selectByBusiness_platTest:" + response);
        System.out.println("selectByBusiness_platTestEntity:" + response.getEntity(String.class));
    }
}
