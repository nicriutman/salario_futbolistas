
package salario_futbolistas;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Salario_futbolistas {

    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {       
        JSONArray  jsonarray = leer_json();        
        System.out.println(jsonarray);
       /*  Map<String,String> map = new HashMap<String,String>();
         Iterator iter = jsonObject.;
 while(iter.hasNext()){
   String key = (String)iter.next();
   String value = jsonObject.getString(key);
   map.put(key,value);
 }*/
	} 
    
    public static JSONArray  leer_json() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();      
	Object obj = parser.parse(new FileReader("informacion.json"));
	JSONArray  jsonarray = (JSONArray ) obj;                        
        return jsonarray ;
            
}
    }

    
    /*String blog = (String) jsonObject.get("Blog");
			System.out.println(blog);

			String temas = (String) jsonObject.get("Temas");
			System.out.println(temas);
			
			long inicio = (Long) jsonObject.get("Inicio");
			System.out.println(inicio);

			JSONObject innerObject = (JSONObject) jsonObject.get("Posts");
			System.out.println(innerObject.toJSONString());
			
			// loop array
			JSONArray tags = (JSONArray) jsonObject.get("Tags");
			Iterator<String> iterator = tags.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}*/

