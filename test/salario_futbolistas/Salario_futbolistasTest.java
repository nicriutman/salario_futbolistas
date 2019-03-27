
package salario_futbolistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static salario_futbolistas.Salario_futbolistas.calcular_salario_completo;
import static salario_futbolistas.Salario_futbolistas.convercion_a_map;
import static salario_futbolistas.Salario_futbolistas.leer_json;
import static salario_futbolistas.Salario_futbolistas.porcentage_total;


public class Salario_futbolistasTest {
    
    public Salario_futbolistasTest() throws IOException, FileNotFoundException, ParseException {        
        
    }            
    @Test
    public void testCalcular_salario_completo() throws IOException, FileNotFoundException, ParseException { // 
        HashMap<String, Integer> mapa_de_niveles = new HashMap<String,Integer>();
        mapa_de_niveles.put("A",5);
        mapa_de_niveles.put("B",10);
        mapa_de_niveles.put("C",15);
        mapa_de_niveles.put("Cuauh",20);
        String ruta_del_archivo_json ="C:\\Users\\USER\\Documents\\NetBeansProjects\\salario_futbolistas\\informacion.json";
        JSONArray  jsonarray_de_json = leer_json(ruta_del_archivo_json);
        HashMap<String, json> mapa_de_json = convercion_a_map(jsonarray_de_json); 
        HashMap<String, Double> mapa_porcentajes_totales = porcentage_total(mapa_de_json,mapa_de_niveles);
        HashMap<String, json> map_de_json_con_sueldo_completo = calcular_salario_completo(mapa_de_json,mapa_porcentajes_totales);
        HashMap<String,Float> nombre_salario = new HashMap<String,Float>();        
    HashMap<String, Float> esperado_map_de_json_con_sueldo_completo = new HashMap<String, Float>();     
    esperado_map_de_json_con_sueldo_completo.put("Juan Perez", 72333.336f);
    esperado_map_de_json_con_sueldo_completo.put("EL Cuauh", 139300.0f);
    esperado_map_de_json_con_sueldo_completo.put("Cosme Fulanito", 32600.0f);
    esperado_map_de_json_con_sueldo_completo.put("El Rulo", 45150.0f);
    for(int i =0;i<map_de_json_con_sueldo_completo.size();i++){
     nombre_salario.put(map_de_json_con_sueldo_completo.get(i).getNombre(), map_de_json_con_sueldo_completo.get(i).getSueldo_completo());
    }
    assertTrue(esperado_map_de_json_con_sueldo_completo.equals(nombre_salario));        
    }             
}
