package test.reqres_calls;


import javax.ws.rs.client.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


class request {
    static Client client = ClientBuilder.newClient();
    static WebTarget target= client.target("https://reqres.in");

    void get(int key, int SearchByid) {

        try {
            String path="?page=";
            if(SearchByid==1){
                path="/";
            }
            WebTarget target = this.target.path("/api/users"+path+key);
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() != 200) {
                throw new RuntimeException("HTTP error code : " + response.getStatus());
            }
            System.out.println(response.getStatus() + "\n");
            System.out.println(response.readEntity(String.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     void get() {
        get(1,0);
    }
    void post(String input){
        WebTarget target = this.target.path("/api/users");
        Invocation.Builder invocationBuilder=target.request(MediaType.APPLICATION_JSON);
        Response response=invocationBuilder.post(Entity.entity(input,MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.getStatus() + "\n");
        System.out.println(response.readEntity(String.class));
    }
    void post(){
        String input="{\"name\":\"morpheus\",\"job\":\"leader\"}";
        post(input);
    }
    void delete(){
        WebTarget webTarget = this.target.path("/api/users/2");
        Invocation.Builder invocationBuilder =  webTarget.request();
        Response response = invocationBuilder.delete();
        System.out.println(response.getStatus());
    }
    void put(){
        String input="{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
        WebTarget webTarget = this.target.path("/api/users/2");
        Invocation.Builder invocationBuilder=target.request(MediaType.APPLICATION_JSON);
        Response response=invocationBuilder.post(Entity.entity(input,MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.getStatus() + "\n");
        System.out.println(response.readEntity(String.class));
    }

}

public class UsingJersey {
    public static void main(String[] args) {
        request obj=new request();
        obj.get(2,1);
    }
}
