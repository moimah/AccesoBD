package other.classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class ManageResidenciasEscolares {
	
	private Connection connection;
	private String uriBD;
	private String user;
	private String password;
	char typeConex;
		
	/**Abre la conexion Insertando los parametros:
	 * @param uriBD
	 * @param user
	 * @param password
	 * @param typeConex
	 * @return 
	 */
	public boolean abrirConexion(String uriBD, String user, String password, char typeConex) {	
		
	boolean conectado = false;	
	//TODO aqui poner string de conexion verdadero
		try {
			
			switch (typeConex) {			
			case 'a': //BD en mysql					
				this.uriBD = uriBD;
				this.user = user;
				this.password = password;
				this.typeConex = typeConex;
				
				Class.forName("com.mysql.cj.jdbc.Driver"); 			
				this.connection = DriverManager.getConnection("jdbc:mysql://".concat(uriBD).concat("?serverTimezone=GMT") , user, password);
				if(!connection.isClosed()) {
					//System.out.println("Conexión establecida");
					conectado = true;
				}
				
				break;
			case  'b': //BD en sql server
				
				this.uriBD = uriBD;
				this.user = user;
				this.password = password;
				this.typeConex = typeConex;
				
				try {
					 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");						
						//this.connection = DriverManager.getConnection("jdbc:sqlserver://" + uriBD + ";user=" + user + ";password=" + password); 	
						this.connection = DriverManager.getConnection("jdbc:sqlserver://" + uriBD + ";user=" + user + ";password=" + password);
			            System.out.println("Conexion Exitosa");			            
						conectado = true;
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				 			        
			        				         
			       
				
			   break;
			   
			case 'c': //BD en Base
				
				System.out.println("Ha seleccionado C");
				

				this.uriBD = uriBD;
				this.user = user;
				this.password = password;
				this.typeConex = typeConex;
				 			        
			        	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");		  
												
			        	 //this.connection = DriverManager.getConnection("jdbc:ucanaccess://database/bdResidenciasEscolares.accdb");
						this.connection = DriverManager.getConnection("jdbc:ucanaccess://"+ uriBD);
						
						
			            System.out.println("Conexion Exitosa");			            
						conectado = true;		
				
				
			
			}
			
			
		} catch (Exception e) {
			System.out.println("Problema en la conexión");
			e.printStackTrace();
		}
		
		
		return conectado;	
				
	}
	
	/**
	 * Abre la conexion con los atributos seteados
	 * @return true si se ha abierto, false si no
	 */
	
	public boolean abrirConexion() {	
		
		boolean conectado = false;	
		//TODO aqui poner string de conexion verdadero
			try {
				
				switch (typeConex) {			
				case 'a': //BD en mysql					
					this.uriBD = uriBD;
					this.user = user;
					this.password = password;
					this.typeConex = typeConex;
					this.connection = DriverManager.getConnection("jdbc:mysql://".concat(uriBD), user, password);
					if(!connection.isClosed()) {
						System.out.println("Conexión establecida");
						conectado = true;
					}
					
					break;
				case  'b': //BD en SQL server							
					this.uriBD = uriBD;
					this.user = user;
					this.password = password;
					this.typeConex = typeConex;
					this.connection =  DriverManager.getConnection("jdbc:sqlserver://" + uriBD + ";user=" + user + ";password=" + password);
					if(!connection.isClosed()) {
						System.out.println("Conexión establecida");
						conectado = true;
					}
					
				   break;
				   
				case 'c': //BD en Access
					this.connection =  DriverManager.getConnection("jdbc:ucanaccess://" + uriBD );
					
				default: System.out.println("No se ha seleccionado conexión");
				}
				
				
			} catch (SQLException e) {
				System.out.println("Problema en la conexión");
			//	e.printStackTrace();
			}
			
			
			return conectado;	
					
		}
	
	/**Cierra la conexion con la BD	 * 
	 * @return true if is closed
	 */
	public boolean cerrarConexion() {
				
		try {
			if(!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha cerrado la conexión");
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	/**Realiza una consulta sobre la db y convierte los 
	 * resultados en objetos residenciaAmpliada
	 * los añade a un lista la cual retorna
	 * @return listaResidenciasAmpliadas<ResidenciaAmpliada>
	 */
	
	public ArrayList<ResidenciaAmpliada> listarResidenciasAmpliada() {
						
		ArrayList<ResidenciaAmpliada> listaResidenciasAmpliadas = new ArrayList<ResidenciaAmpliada>();
		
			try {
			
				if(connection.isClosed()) { //Si la conexión esta cerrada
					abrirConexion(uriBD, user, password, typeConex); //Abrimos conexion
				}
							
				
			String sql = "SELECT t1.codResidencia, t1.codUniversidad,  t2.nomUniversidad, t1.nomResidencia, t1.precioMensual, t1.comedor FROM residencias AS t1 INNER JOIN universidades AS t2 ON t1.codUniversidad = t2.codUniversidad ORDER BY t1.codResidencia";
			Statement statement = connection.createStatement(); //Creamos la sentencia
			ResultSet result = statement.executeQuery(sql); //Ejecutamos la sentencia y la almacenamos en un una lista
			
			//Se extrae contenido de resultSet y se almacena en una lista de residenciaAmpliada		
			while(result.next()) {  
				ResidenciaAmpliada r = new ResidenciaAmpliada();
				r.setCodResidencia(result.getInt(1));
				r.setCodUniversidad(result.getString(2));
				r.setNombreUniversidad(result.getString(3));
				r.setNombreResidencia(result.getString(4));
				r.setPrecioMensual(result.getInt(5));
				r.setComedor(result.getBoolean(6));
				listaResidenciasAmpliadas.add(r);
			}
			//Cerramos conexion y liberamos recursos			
			connection.close();
			result.close();
			statement.close();
			
			System.out.println("Se ha realizado la consulta");
			
			}catch (SQLException e) {
				System.out.println("Problema en la consulta");
				e.printStackTrace();
			}
					
		
		return listaResidenciasAmpliadas;
			
	}
	
	/**Realiza una consulta sobre la db y convierte los 
	 * resultados en objetos universidad
	 * los añade a un lista la cual retorna
	 * @return listaUniversidades<Universidad>
	 */
	
	public ArrayList<Universidad> listarUniversidades() {
		
		ArrayList<Universidad> listaUniversidades = new ArrayList<Universidad>();
			
			try {
				
				if(connection.isClosed()) { //Si la conexión esta cerrada
					abrirConexion(uriBD, user, password, typeConex); //Abrimos conexion
				}
				
				String sql = "SELECT * from universidades";
				Statement statement = connection.createStatement(); //Creamos la sentencia
				ResultSet result = statement.executeQuery(sql); //Ejecutamos la sentencia y la almacenamos en un una lista
				
				//Se recorre el resultset y se crea una lista
				//de objetos universidad con el contenido
				
				while(result.next()) {
					Universidad uni = new Universidad();
					uni.setCodUniversidad(result.getString(1));
					uni.setNombreUniversidad(result.getString(2));
					listaUniversidades.add(uni);
				}
				
				//Cerramos conexion y liberamos recursos
				connection.close();
				result.close();
				statement.close();
				
				
				System.out.println("Se ha realizado la consulta");
				
							
				
			} catch (SQLException e) {
				System.out.println("Problema en la consulta");
				e.printStackTrace();
			}
								
		
		return listaUniversidades;
		
	}
	
	/**
	 * Realiza una inserción de residencias
	 * usando sentencias preparadas
	 * @param r
	 * @return true si se inserto, false si no
	 */
	
	public boolean insertarResidencia(Residencia r) {
		
		boolean insertado = false;
		
		try {
			
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}					
			
			String sql = "INSERT INTO residencias(nomResidencia, codUniversidad, precioMensual, comedor) VALUES(?, ?, ?, ?)"; //Creamos la setencia preparada			
			PreparedStatement preparedStatement = connection.prepareStatement(sql); //Cargamos la sentencia
			
			//Rellenamos sentencia preparada
		
				preparedStatement.setString(1, r.getNombreResidencia());		
				preparedStatement.setString(2, r.getCodUniversidad());			
				preparedStatement.setInt(3, r.getPrecioMensual());	
				preparedStatement.setBoolean(4, r.isComedor());
			
			   int x = preparedStatement.executeUpdate();
				if(x>0) { //Si se ha insertado alguna residencia
					insertado=true;
				}
			   
				//Cerramos conexion y liberamos recursos
			   connection.close();
			   preparedStatement.close(); 
			
			System.out.println("Se ha insertado: " + x + " residencias");
			
		} catch (SQLException e) {
			System.out.println("Problema en la inserción");
			
			e.printStackTrace();
		}
		
		return insertado;
		
	}
	
	/**Comprueba si una residencia contiene estancias
	 * introduciendo un objeto residencia	 
	 * @param r
	 * @return true si contiene, false si no
	 */
	
	public boolean comprobarEstancias(Residencia r) {
		
		boolean contiene = false; 
		try{
			
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
								
			
			String sql = "SELECT COUNT(*) FROM estancias WHERE codResidencia = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setInt(1, r.getCodResidencia());
			ResultSet resultado = preparedStatement.executeQuery();
			
			int numEstancias = 0; 
			while(resultado.next()) {
				numEstancias = resultado.getInt(1);
			}
			
			if(numEstancias>0) {
				contiene = true;
			}
			
			//Cerramos conexion y liberamos recursos
			connection.close();
			preparedStatement.close();
			resultado.close();
			
		    
		    System.out.println("Contiene estancias:" +  numEstancias);
			
		
		}catch (SQLException e) {
			System.out.println("Problema en la consulta");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contiene; 
		
	}
	

	/**Elimina una residencia a partir de un objeto
	 * residencia insertado como parametro, d
	 * @param r
	 * @return true si se elimino, false si no
	 */
	public boolean eliminarResidencia(Residencia r) {
		
		boolean eliminado = false;
		try {
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			//String sql = "DELETE FROM residencias WHERE codResidencia = ?";
			String sql = "DELETE FROM residencias WHERE nomResidencia = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setInt(1, r.getCodResidencia());
			preparedStatement.setString(1, r.getNombreResidencia());
			int x = preparedStatement.executeUpdate();
			
			if(x>0) {
				eliminado=true;
			}
			
			//Cerramos conexion y liberamos recursos
			connection.close();
			preparedStatement.close();
			
			System.out.println("Se ha eliminado " + x + " residencias");
			
			
			
		}catch (SQLException e) {
			System.out.println("Problema en el borrado");			
			//e.printStackTrace();
		}
		
		return eliminado; 
	}
	
	/**
	 * Busca una residencia a partir de su nombre
	 * Devuelve un objeto residencia con los resultados
	 * @param nombreResidencia
	 * @return R
	 */
	
	public Residencia buscarResidencia(String nombreResidencia) {
		boolean encontrada = false; 
		Residencia r = new Residencia();
		
		try {
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
		 String sql = "SELECT * FROM residencias WHERE nomResidencia = ?";
		 PreparedStatement preparedStatment = connection.prepareStatement(sql);
		 preparedStatment.setString(1, nombreResidencia);
		 ResultSet resultado = preparedStatment.executeQuery();
		 
		 while(resultado.next()) {
			 r.setCodResidencia(resultado.getInt(1));
			 r.setNombreResidencia(resultado.getString(2));
			 r.setCodUniversidad(resultado.getString(3));
			 r.setPrecioMensual(resultado.getInt(4));
			 r.setComedor(resultado.getBoolean(5));
			 encontrada = true; //Esto es opcional
		 }
		 
		 //Cerramos la conexión y liberamos los recursos
		 connection.close();
		 preparedStatment.close();
		 resultado.close();		 
		 
		 System.out.println("Encontrar residencia: " + encontrada);
		 
			
		} catch (SQLException e) {
			System.out.println("Problema en la busqueda");			
			e.printStackTrace();
		}
		
		return r;
		
	}
	
	
	/**Modifica una residencia a partir de un objeto
	 * residencia insertado como parametro
	 * @param r
	 * @return actualizado=true si se modifico, resultado=false si no
	 */
	
	public boolean actualizarResidencia(Residencia r) {
		boolean actualizado = false;
		try {
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			String sql = "UPDATE residencias SET nomResidencia = ?, codUniversidad = ?, precioMensual = ?,  comedor = ? WHERE codResidencia = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(5, r.getCodResidencia());
			preparedStatement.setString(1, r.getNombreResidencia());
			preparedStatement.setString(2, r.getCodUniversidad());
			preparedStatement.setInt(3,r.getPrecioMensual());
			preparedStatement.setBoolean(4, r.isComedor());
			int x = preparedStatement.executeUpdate();
			
			if(x>0) {
				actualizado=true;
			}
			
			//Cerramos conexion y liberamos recursos
			connection.close();
			preparedStatement.close();
			
			System.out.println("Se ha actualizado " + x + " residencias");
			
			
			
		}catch (SQLException e) {
			System.out.println("Problema en la modificación");			
			e.printStackTrace();
		}
		
		return actualizado;
	}
	
	public String buscarCodigoUniversidad(String nombreUniversidad) {
		boolean encontrada = false; 
		String codUniversidad = null; 
		
		try {
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
		 String sql = "SELECT codUniversidad FROM universidades WHERE nomUniversidad = ?";
		 PreparedStatement preparedStatment = connection.prepareStatement(sql);
		 preparedStatment.setString(1, nombreUniversidad);
		 ResultSet resultado = preparedStatment.executeQuery();
		 
		 while(resultado.next()) {			 
			 codUniversidad = resultado.getString(1);			
			 encontrada = true; //Esto es opcional
		 }
		 
		 //Cerramos la conexión y liberamos los recursos
		 connection.close();
		 preparedStatment.close();
		 resultado.close();		 
		 
		 System.out.println("Buscar codigo universidad: " + encontrada);
		 
			
		} catch (SQLException e) {
			System.out.println("Problema en la busqueda");			
			e.printStackTrace();
		}
		
		return codUniversidad;
		
	}
	
	
	public String buscarNombreUniversidad(String codUniversidad) {
		boolean encontrada = false; 
		String nombreUniversidad = null; 
		
		try {
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
		 String sql = "SELECT nomUniversidad FROM universidades WHERE codUniversidad = ?";
		 PreparedStatement preparedStatment = connection.prepareStatement(sql);
		 preparedStatment.setString(1, codUniversidad);
		 ResultSet resultado = preparedStatment.executeQuery();
		 
		 while(resultado.next()) {			 
			 nombreUniversidad = resultado.getString(1);			
			 encontrada = true; //Esto es opcional
		 }
		 
		 //Cerramos la conexión y liberamos los recursos
		 connection.close();
		 preparedStatment.close();
		 resultado.close();		 
		 
		 System.out.println("Buscar codigo universidad: " + encontrada);
		 
			
		} catch (SQLException e) {
			System.out.println("Problema en la busqueda");			
			e.printStackTrace();
		}
		
		return nombreUniversidad;
		
	}
	
	
	public String buscarCodigoResidencia(String nombreResidencia) {
		boolean encontrada = false; 
		String codResidencia = null; 
		
		try {
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
		 String sql = "SELECT codResidencia FROM residencias WHERE nomResidencia = ?";
		 PreparedStatement preparedStatment = connection.prepareStatement(sql);
		 preparedStatment.setString(1, nombreResidencia);
		 ResultSet resultado = preparedStatment.executeQuery();
		 
		 while(resultado.next()) {			 
			 codResidencia = resultado.getString(1);			
			 encontrada = true; //Esto es opcional
		 }
		 
		 //Cerramos la conexión y liberamos los recursos
		 connection.close();
		 preparedStatment.close();
		 resultado.close();		 
		 
		 System.out.println("Buscar codigo residencia: " + encontrada);
		 
			
		} catch (SQLException e) {
			System.out.println("Problema en la busqueda");			
			e.printStackTrace();
		}
		
		return codResidencia;
		
	}
	
	
	/**
	 * Realiza una inserción de residencias
	 * ejecutando un procedimiento almacenado	
	 * @param r
	 * @return insertado=true si se inserto, 
	 * insertado = false si no
	 */	
	public boolean procedimientoInsertar(Residencia r) {

		boolean insertado = false;

		try {

			if (connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
			String sql = "";
			
			switch (typeConex) {
			case 'a':
				sql = "CALL insertarResidencia (?, ?, ?, ?, ?, ?)";
				break;

			case 'b':
				sql = "exec insertarResidencia ?, ?, ?, ?, ?, ?";
				break;
			}
						
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, r.getNombreResidencia());
			callableStatement.setString(2, r.getCodUniversidad());
			callableStatement.setInt(3, r.getPrecioMensual());
			callableStatement.setBoolean(4, r.isComedor());
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();

			int existe = callableStatement.getInt(5);
			int insertar = callableStatement.getInt(6);
			
			if(existe>0 && insertar>0) {
				insertado = true; 
			}
			
		} catch (Exception e) {

		}

		return insertado;

	}
	/**
	 * Ejecuta un procedimiento almacenado en la db
	 * añade a una lista los alojamientos coincidentes
	 * con el parametro dni	
	 * @param dni
	 * @return listaAlojamientos<alojamiento>
	 */
	
	public ArrayList<Alojamiento> procedimientoVisualizarEstancias(String dni) {
		
		ArrayList<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();
		boolean modificar = false;		
		
		try {
			
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
			String sql ="";
			switch (typeConex) {
			case 'a':
				sql = "CALL listarEstancias (?)";
				break;

			case 'b':
				sql = "exec listarEstancias ?";
				break;
			}
										
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, dni);	
			ResultSet resultado = callableStatement.executeQuery();
			
			while(resultado.next()) {
				Alojamiento a = new Alojamiento();
				a.setNombreResidencia(resultado.getString(1));
				a.setNombreUniversidad(resultado.getString(2));
				a.setFechaInicio(resultado.getDate(3));
				a.setFechaFin(resultado.getDate(4));
				a.setPrecioPagado(resultado.getInt(5));
				listaAlojamientos.add(a); 
				modificar = true; //No es necesario				
			}
			
			if(listaAlojamientos!=null) {
				System.out.println("Se han añadido alojamientos a la lista");
			}
									   
			//Cerramos conexion y liberamos recursos
			   connection.close();
			   callableStatement.close(); 
			 
		
		} catch (SQLException e) {
			System.out.println("Problema en la ejecución del procedimiento");			
			e.printStackTrace();
		}
		
		return listaAlojamientos;
		
	}
	
	
	/**
	 * Ejecuta un procedimiento almacenado en la BD que
	 * a partir de parametros de entrada nombreUniversidad
	 * y precio devuelve el nº de residencias de una universidad	
	 * y el numero de residencias a un precio inferior al indicado
	 * @param nombreUniversidad
	 * @param precio
	 * @return devolver[0] numero de residencias, devolver[1] precio menor al indicado	
	 */	
	
	public int[] procedimientoCantidadResidenciasPrecio(String nombreUniversidad, int precio) {
		
		int[] devolver = new int[2];
		
		try {
			
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
			String sql = ""; 
			
			switch (typeConex) {
			case 'a':
			sql = "CALL cantidadResidencias(?, ?, ?, ?)";
				break;
			case 'b':
				sql = "exec cantidadResidencias ?, ?, ?, ?";
				break;

			
			}
			
			
			CallableStatement callableStatement = connection.prepareCall(sql); //Cargamos la sentencia
			
			//Rellenamos sentencia preparada
		
				callableStatement.setString(1, nombreUniversidad);		
				callableStatement.setInt(2, precio);
				callableStatement.registerOutParameter(3, Types.BIGINT);
				callableStatement.registerOutParameter(4, Types.BIGINT);
				callableStatement.execute();				
		
			    devolver[0] = callableStatement.getInt(3);
			    devolver[1] = callableStatement.getInt(4);
			
			System.out.println("La universidad " + nombreUniversidad + " tiene " + devolver[0] + " residencias");
			System.out.println("La universidad " + nombreUniversidad + " tiene " + devolver[1] + " residencias a un precio inferior a " + precio);
							   
			//Cerramos conexion y liberamos recursos
			   connection.close();
			   callableStatement.close(); 
			   
			
			
			
		} catch (SQLException e) {
			System.out.println("Problema en la ejecución del procedimiento");
			e.printStackTrace();
		}
		
		return devolver;
				
	}
	
	/**
	 * Ejecuta una funcion SQL que devuelve el tiempoHospedado
	 * @param dni
	 * @return numero de meses hospedado de un alumno
	 */
	
	public int funcionTiempoHospedado(String dni) {
		
		int tiempoHospedado = 0; 
		
		try {
			
			if(connection.isClosed()) {
				abrirConexion(uriBD, user, password, typeConex);
			}
			
			String sql = "";
			
			switch (typeConex) {
			case 'a':
				sql = "SELECT tiempoHospedado(?)";
				break;

			case 'b':
				sql = "SELECT dbo.tiempoHospedado (?)";
				break;
			}
			
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dni);
			ResultSet resultado = preparedStatement.executeQuery();
			
			while(resultado.next()) {
				tiempoHospedado = resultado.getInt(1);
			}
			
			//Cerramos conexion y liberamos recursos
			connection.close();
			preparedStatement.close();
			resultado.close();
			
			System.out.println("El estudiante con DNI " + dni + " se ha hospedado " + tiempoHospedado + " meses" ); 
						
			
	}catch (SQLException e) {
		System.out.println("Problema en la ejecución la función");
		e.printStackTrace();
	}
		
	return tiempoHospedado; 
		
	}

	public Connection getConnection() {
		return connection;
	}

	public String getUriBD() {
		return uriBD;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public char getTypeConex() {
		return typeConex;
	}
	
	
	

}
