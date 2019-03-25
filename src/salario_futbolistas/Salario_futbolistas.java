
package salario_futbolistas;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import static oracle.jrockit.jfr.events.Bits.intValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Salario_futbolistas {

    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {       
        HashMap<String, json> mapa_de_niveles = capturar_niveles();        
        String ruta = ruta_de_json();
        JSONArray  jsonarray = leer_json(ruta);
        HashMap<String, json> map_de_json = convercion_a_map(jsonarray); 
        HashMap<String, Double> map_porcentajes_totales = porcentage_total(map_de_json,mapa_de_niveles);        
	HashMap<String, json> map_de_json_con_sueldo_completo = calcular_salario_completo(map_de_json,map_porcentajes_totales);
        mostrar_map_json_sueldo_completo(map_de_json_con_sueldo_completo);
    }  
    
    public static void mostrar_map_json_sueldo_completo(HashMap map_de_json_con_sueldo_completo){
        HashMap<String, json> map_de_json = map_de_json_con_sueldo_completo;
        for(int i =0;i<map_de_json.size();i++){
          System.out.println(map_de_json.get(i));
        }
    }
    
    public static HashMap calcular_salario_completo(HashMap map_json, HashMap map_porcentajes){
        HashMap<String, json> map_de_json = map_json;
        HashMap<String, Float> map_porcentaje = map_porcentajes;
        float salario_completo;
        for(int i =0;i<map_de_json.size();i++){
        salario_completo = (float)(map_porcentaje.get(map_de_json.get(i).getNombre())/100);  
        salario_completo =(float)(salario_completo*map_de_json.get(i).getBono());
        salario_completo =(float)(salario_completo+map_de_json.get(i).getSueldo());
        map_de_json.get(i).setSueldo_completo(salario_completo);
        }
        return map_de_json;        
    }
    
    public static HashMap porcentage_total(HashMap map,HashMap map2){
        double porcentaje_grupal = 0;
        double porcentaje_individual = 0;
        float porcentaje_total = 0;
        int total_goles_individuales =0;
        int meta_de_goles=0;
        HashMap<String, json> map_de_json = map;
        HashMap<String, Integer> map_de_niveles = map2;
        HashMap<String, Float> map_porcentajes_totales = new HashMap<String,Float>();
        for (int i =0;i<map_de_json.size();i++){
          total_goles_individuales = total_goles_individuales + map_de_json.get(i).getGoles(); 
          meta_de_goles = meta_de_goles + map_de_niveles.get(map_de_json.get(i).getNivel());          
        }          
        porcentaje_grupal =(double) total_goles_individuales/meta_de_goles;
        porcentaje_grupal =(double) porcentaje_grupal*100;        
        for(int i =0;i<map_de_json.size();i++){            
          porcentaje_individual =(double)  map_de_json.get(i).getGoles()/map_de_niveles.get(map_de_json.get(i).getNivel());
          porcentaje_individual =(double)  porcentaje_individual*100;          
          porcentaje_total = (float) (porcentaje_individual+porcentaje_grupal);          
          porcentaje_total =(float) (porcentaje_total/2);           
          map_porcentajes_totales.put(map_de_json.get(i).getNombre(),porcentaje_total);
        }
        return map_porcentajes_totales;        
    }
    
    public static HashMap capturar_niveles(){
        Scanner reader = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>(); 
        System.out.println("ingrese el valor de los niveles");
        System.out.println("A=");                                              
        map.put("A",reader.nextInt());
        System.out.println("B=");
        map.put("B",reader.nextInt());
        System.out.println("C=");        
        map.put("C",reader.nextInt());
        System.out.println("Cuauh=");
        map.put("Cuauh",reader.nextInt());
        return map;        
    }
    
    public static String ruta_de_json(){   
        System.out.println("copia la ruta del archivo json");
        Scanner reader = new Scanner(System.in);
        String ruta = reader.nextLine();
        return ruta;        
    }
    
    public static HashMap convercion_a_map (JSONArray jsonarray) throws IOException, FileNotFoundException, ParseException{
          JSONArray  jsonarray2 = jsonarray;
          JSONObject obj = new JSONObject(); 
          HashMap<Integer, json> map = new HashMap<Integer, json>(); 
          String nombre;
          String nivel;
          int goles;
          int sueldo;
          int bono;
          int sueldo_completo;
          String equipo;
           for(int i = 0;i<jsonarray2.size();i++){           
           obj = (JSONObject) jsonarray2.get(i); 
           nombre = (String) obj.get("nombre");
           nivel = (String) obj.get("nivel");           
           goles = intValue(obj.get("goles"));
           sueldo = intValue(obj.get("sueldo"));
           bono = intValue(obj.get("bono"));
           sueldo_completo = intValue(obj.get("sueldo_completo"));
           equipo = (String) obj.get("equipo");
           map.put(i,new json(nombre,nivel,goles,sueldo,bono,sueldo_completo,equipo));            
        }  
        return map;                      
          }             
    
    public static JSONArray  leer_json(String ruta) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();      
	Object obj = parser.parse(new FileReader(ruta));        
	JSONArray  jsonarray = (JSONArray ) obj;                        
        return jsonarray ;            
  }
}

   