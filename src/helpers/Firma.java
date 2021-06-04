package helpers;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;

public class Firma {

	private static Signature firma;
	private static byte[] bytes_firma;
	private static byte[] bytes_informacion;
	private static byte[] bytes_firma_valida;
	
	//LISTA QUE CONTIENE LAS FIRMAS DIGITALES DE LOS ARCHIVOS ENCRIPTADOS Y FIRMADOS
	private static ArrayList<byte[]> bytes_firmas_digitales = new ArrayList<>();
	
	//METODO PARA INSTANCIAR LA FIRMA (OBJETO SIGNATURE)
	public static void instanciarFirma() {
		try {
			if(firma==null) {
				firma = Signature.getInstance("SHA256withRSA");
			}else {
				System.out.println("Ya la firma fue instanciada previamente");
			}
		} catch (NoSuchAlgorithmException e) {

		}
	}
	
	//METODO PARA CREAR UNA FIRMA DIGITAL CON LOS BYTES DE ENTRADA DE UN ARCHIVO
	public static void crearFirma(byte[] entrada) {
		try {
			firma.initSign(Llaves.obtenerLlavePrivada());
			bytes_informacion = entrada;  
			firma.update(bytes_informacion);
			bytes_firma = firma.sign();
			//SE INCLUYEN LOS BYTES DE LA FIRMA DIGITAL EN LA LISTA
			bytes_firmas_digitales.add(bytes_firma);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//METODOS ESTATICOS PARA OBTENER LOS BYTES DE LAS FIRMAS
	public static byte[] obtenerBytesFirma() {
		return bytes_firma;
	}
	
	public static byte[] obtenerBytesFirmaValida() {
		return bytes_firma_valida;
	}
	
	//METODO PARA VERIFICAR UNA FIRMA DIGITAL EN BASE A LOS BYTES DE ENTRADA DE UN ARCHIVO ENCRIPTADO
	public static Boolean verificarFirma(byte[] entrada) {
		try {
			byte[] bytes_informacion_archivo_encriptado = entrada;  
			firma.initVerify(Llaves.obtenerLlavePublica());
			firma.update(bytes_informacion_archivo_encriptado);
			
			//VALIDAR LOS BYTES DE ENTRADA CON LAS FIRMAS DIGITALES ALMACENADAS EN LA LISTA
			for(int i=0; i<bytes_firmas_digitales.size(); i++) {
				System.out.println("Verificando la firma digital " + i);
				System.out.println("Firma digital "+i+": "+bytes_firmas_digitales.get(i));
				if(firma.verify(bytes_firmas_digitales.get(i))) {
					bytes_firma_valida=bytes_firmas_digitales.get(i);
					return true;
				}else {
					firma.initVerify(Llaves.obtenerLlavePublica());
					firma.update(bytes_informacion_archivo_encriptado);
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
