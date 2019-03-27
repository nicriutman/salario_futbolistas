
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
        String ruta_del_archivo_json = ruta_de_json();
        JSONArray  jsonarray_de_json = leer_json(ruta_del_archivo_json);
        HashMap<String, json> mapa_de_json = convercion_a_map(jsonarray_de_json); 
        HashMap<String, Double> mapa_porcentajes_totales = porcentage_total(mapa_de_json,mapa_de_niveles);      
        System.out.println("mapa_porcentajes_totales"+mapa_porcentajes_totales);
	HashMap<String, json> map_de_json_con_sueldo_completo = calcular_salario_completo(mapa_de_json,mapa_porcentajes_totales);
        System.out.println("map_de_json_con_sueldo_completo"+map_de_json_con_sueldo_completo);
        mostrar_map_json_sueldo_completo(map_de_json_con_sueldo_completo);
    }  
    
    public static void mostrar_map_json_sueldo_completo(HashMap map_de_json_con_sueldo_completo){
        HashMap<String, json> map_de_json = map_de_json_con_sueldo_completo;
        for(int i =0;i<map_de_json.size();i++){
          System.out.println(map_de_json.get(i).getNombre()+" tiene un sueldo total de = "+map_de_json.get(i).getSueldo_completo());
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
    
    public static HashMap porcentage_total(HashMap mapa_de_json,HashMap mapa_de_niveles){
        double porcentaje_grupal = 0;
        double porcentaje_individual = 0;
        float porcentaje_total = 0;
        int total_goles_individuales =0;
        int meta_de_goles=0;
        HashMap<String, json> map_de_json = mapa_de_json;
        HashMap<String, Integer> map_de_niveles = mapa_de_niveles;
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
        Scanner captura_de_datos = new Scanner(System.in);
        HashMap<String, Integer> mapa_de_niveles = new HashMap<String, Integer>(); 
        System.out.println("ingrese el valor de los niveles");
        System.out.println("A=");                                              
        mapa_de_niveles.put("A",captura_de_datos.nextInt());
        System.out.println("B=");
        mapa_de_niveles.put("B",captura_de_datos.nextInt());
        System.out.println("C=");        
        mapa_de_niveles.put("C",captura_de_datos.nextInt());
        System.out.println("Cuauh=");
        mapa_de_niveles.put("Cuauh",captura_de_datos.nextInt());
        return mapa_de_niveles;        
    }
    
    public static String ruta_de_json(){   
        System.out.println("copia la ruta del archivo json");
        Scanner captura_de_datos = new Scanner(System.in);
        String ruta = captura_de_datos.nextLine();
        return ruta;        
    }
    
    public static HashMap convercion_a_map (JSONArray jsonarray) throws IOException, FileNotFoundException, ParseException{
          JSONArray  jsonarray_a_convertir = jsonarray;
          JSONObject objeto_json_transitorio = new JSONObject(); 
          HashMap<Integer, json> mapa_del_json = new HashMap<Integer, json>();                     
           for(int i = 0;i<jsonarray_a_convertir.size();i++){
           objeto_json_transitorio = (JSONObject) jsonarray_a_convertir.get(i);
           mapa_del_json.put(i,new json((String) objeto_json_transitorio.get("nombre"),(String) objeto_json_transitorio.get("nivel"),intValue(objeto_json_transitorio.get("goles")),intValue(objeto_json_transitorio.get("sueldo")),intValue(objeto_json_transitorio.get("bono")),intValue(objeto_json_transitorio.get("sueldo_completo")),(String) objeto_json_transitorio.get("equipo")));            
        }  
        return mapa_del_json;                      
          }             
    
    public static JSONArray  leer_json(String ruta) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();      
	Object objeto_json = parser.parse(new FileReader(ruta));        
	JSONArray  jsonarray = (JSONArray ) objeto_json;                        
        return jsonarray ;            
  }
}

   