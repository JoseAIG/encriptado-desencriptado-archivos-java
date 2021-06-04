package helpers;

import javax.crypto.Cipher;

public class Cripto {

	private static Cipher cipher;
	private static Cipher cipherAES;

	//METODO PARA INSTANCIAR CIPHER CON ALGORITMO RSA PARA USO EN FIRMAS DIGITALES CON LLAVES ASIMETRICAS
	public static void instanciarCipher() {
		try {
			if(cipher==null) {
				cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			}else {
				System.out.println("Cipher ya fue instanciado previamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//METODO PARA ENCRIPTAR USANDO LLAVES ASIMETRICAS (RSA)
	public static byte[] encriptar(byte[] entrada) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, Llaves.obtenerLlavePublica());
			cipher.update(entrada);
		    byte[] cipherText = cipher.doFinal();	
		    return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return entrada;
		}
	}
	
	//METODO PARA DESENCRIPTAR USANDO LLAVES ASIMETRICAS (RSA)
	public static byte[] desencriptar(byte[] entrada) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, Llaves.obtenerLlavePrivada());
			cipher.update(entrada);
			byte[] decipheredText = cipher.doFinal();
			return decipheredText;
		} catch (Exception e) {
			e.printStackTrace();
			return entrada;
		}
	}
	
	//AES
	//METODO PARA INSTANCIAR CIPHER CON ALGORITMO AES PARA CIFRADO CON LLAVES SIMETRICAS
	public static void instanciarCipherAES() {
		try {
			if(cipherAES==null) {
				cipherAES = Cipher.getInstance("AES");
			}else {
				System.out.println("CipherAES ya fue instanciado previamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//METODO PARA ENCRIPTAR CON LLAVE SIMETRICA LOS BYTES DE ENTRADA DE UN ARCHIVO
	public static byte[] encriptarAES(byte[] entrada) {
		try {
			cipherAES.init(Cipher.ENCRYPT_MODE, Llaves.obtenerLlaveAES());
			byte[] bytes_encriptados = cipherAES.doFinal(entrada);
			return bytes_encriptados;
		} catch (Exception e) {
			e.printStackTrace();
			return entrada;
		}
	}
	
	//METODO PARA DESENCRIPTAR CON LLAVE SIMETRICA LOS BYTES DE ENTRADA DE UN ARCHIVO ENCRIPTADO
	public static byte[] desencriptarAES(byte[] entrada) {
		try {
			cipherAES.init(Cipher.DECRYPT_MODE, Llaves.obtenerLlaveAES());
			byte[] bytes_desencriptados = cipherAES.doFinal(entrada);
			return bytes_desencriptados;
		} catch (Exception e) {
			return entrada;
		}
	}
}
