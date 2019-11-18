package other.classes;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ManageResidenciasEscolares mre = new ManageResidenciasEscolares();
		//mre.cerrarConexion();
		mre.abrirConexion("localhost/bdresidenciasescolares", "root","", 'a');
		
//		ArrayList<ResidenciaAmpliada> lista = mre.listarResidenciasAmpliada();
		//mre.abrirConexion("localhost/bdresidenciasescolares", "root","", 'a');
		mre.abrirConexion("localhost/bdresidenciasescolares", "root","", 'b');
//		for(int i=0; i<lista.size();i++) {
//			System.out.println(lista.get(i).getPrecioMensual());
//		}
//		
//		ArrayList<Universidad> listaUni = mre.listarUniversidades();
//		
//		for(int i=0; i<listaUni.size();i++) {
//			System.out.println(listaUni.get(i).getNombreUniversidad());
//		}
		
//	 Residencia r = new Residencia(19, "Moises", "SPA002", 699, true);
	 
//	 mre.insertarResidencia(r);
//	 mre.comprobarEstancias(r);
//	mre.eliminarResidencia(r);
	 //mre.actualizarResidencia(r);
	// mre.procedimientoInsertar(r);
	//mre.procedimientoCantidadResidenciasPrecio("Las Palmas", 200);	
	//int z =  mre.funcionTiempoHospedado("54296710C");
	//System.out.println(z);
	 
	// mre.buscarResidencia("Duerme Xalentito");
	//mre.procedimientoVisualizarEstancias("54296710C");
	 
	
		
	}
	
}
