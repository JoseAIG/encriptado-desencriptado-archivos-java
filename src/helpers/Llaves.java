package helpers;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Llaves {

	//RSA (ENCRIPTACION DE LA LLAVE SIMETRICA FIRMA DIGITAL)
	private static KeyPairGenerator generadorParLlaves;
	private static KeyPair parLlaves;
	
	//AES (ENCRIPTACION DEL ARCHIVO)
	private static SecretKey LlaveSecretaAES;
	
	//METODO PARA GENERAR PAR DE LLAVES (ENCRIPTADO ASIMETRICO)
	public static void generarParLlaves() {
		try {
			generadorParLlaves = KeyPairGenerator.getInstance("RSA");
			generadorParLlaves.initialize(2048);
			parLlaves = generadorParLlaves.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	//METODOS ESTATICOS PARA RETORNAR EL PAR DE LLAVES (LLAVE PUBLICA Y PRIVADA)
	public static PublicKey obtenerLlavePublica() {
		return parLlaves.getPublic();
	}
	
	public static PrivateKey obtenerLlavePrivada() {
		return parLlaves.getPrivate();
	}
	
	//AES
	//METODO PARA GENERAR LLAVE SIMETRICA 
	public static void generarLlaveAES() {
		try {
			KeyGenerator generadorLlave = KeyGenerator.getInstance("AES");
			generadorLlave.init(128);
			LlaveSecretaAES = generadorLlave.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	//METODO PARA OBTENER LA LLAVE SIMETRICA
	public static SecretKey obtenerLlaveAES() {
		return LlaveSecretaAES;
	}

}
