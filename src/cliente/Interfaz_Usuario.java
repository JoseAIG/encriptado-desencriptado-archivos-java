package cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.*;
import javax.swing.border.*;

import helpers.Cripto;
import helpers.Firma;
import helpers.Llaves;

public class Interfaz_Usuario {

	//ESTRUCTURA DE LA UI
	private static JFrame marcoPrincipal = new JFrame("ENCRIPTADO - DESENCRIPTADO DE ARCHIVOS");
	private static JPanel panelPrincipal = new JPanel();
	private JPanel panelTitulo = new JPanel();
	private JLabel labelTitulo = new JLabel("ENCRIPTADO - DESENCRIPTADO");
	private JPanel panelTrabajo = new JPanel();
	
	//NOMBRE DEL ARCHIVO
	private JPanel panel_NombreArchivo = new JPanel();
	private JLabel label_NombreArchivo = new JLabel("Nombre del archivo:");
	private JTextField textField_NombreArchivo = new JTextField("Contraseñas");

	//EXTENSION DEL ARCHIVO
	private JLabel label_ExtensionArchivo = new JLabel("Extension del archivo:");
	private JPanel panel_ExtensionArchivo = new JPanel();
	private JTextField textField_ExtensionArchivo = new JTextField(".txt");
	
	//RUTA DEL ARCHIVO
	private JLabel label_RutaArchivo = new JLabel("Ruta del archivo:");
	private JPanel panel_RutaArchivo = new JPanel();
	private JTextField textField_RutaArchivo = new JTextField("C:\\Users\\inciarte\\Desktop\\");
	private JButton boton_BackSlash = new JButton("\\");
	
	//BOTONES DE ACCION
	private JButton boton_EncriptarArchivo = new JButton("Encriptar");
	private JButton boton_DesencriptarArchivo = new JButton("Desencriptar");

	//LABELS
	private JLabel label_MiNombre = new JLabel("Jose Inciarte C.I. 27.696.083");
	
	public Interfaz_Usuario() {
		
		//BORDES PARA LA UI
		Border borde1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		Border borde2 = new BevelBorder(BevelBorder.RAISED);
		
		//ELEMENTOS DE LA UI
		marcoPrincipal.getContentPane().setLayout(null);
		marcoPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		marcoPrincipal.setSize(450,478);
		marcoPrincipal.setLocationRelativeTo(null);
		marcoPrincipal.setResizable(false);
		
		panelPrincipal.setLayout(null);
		marcoPrincipal.setContentPane(panelPrincipal);
		
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(10, 10, 418, 50);
		panelTitulo.setBorder(borde1);
		panelPrincipal.add(panelTitulo);
		
		labelTitulo.setBounds(10, 11, 398, 28);
		labelTitulo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 22));
		labelTitulo.setHorizontalAlignment(JLabel.CENTER);
		panelTitulo.add(labelTitulo);
		
		panelTrabajo.setLayout(null);
		panelTrabajo.setBounds(10, 70, 418, 347);
		panelTrabajo.setBorder(borde1);
		panelPrincipal.add(panelTrabajo);
		label_MiNombre.setForeground(Color.LIGHT_GRAY);
		
		label_MiNombre.setBounds(10, 420, 418, 14);
		label_MiNombre.setHorizontalAlignment(JLabel.CENTER);
		panelPrincipal.add(label_MiNombre);
		
		//NOMBRE DEL ARCHIVO
		panel_NombreArchivo.setBorder(borde2);
		panel_NombreArchivo.setLayout(null);
		panel_NombreArchivo.setBounds(10,11,398,83);
		panelTrabajo.add(panel_NombreArchivo);
		
		label_NombreArchivo.setBounds(10, 11, 378, 17);
		label_NombreArchivo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		panel_NombreArchivo.add(label_NombreArchivo);
		
		textField_NombreArchivo.setBounds(10, 37, 378, 35);
		textField_NombreArchivo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		panel_NombreArchivo.add(textField_NombreArchivo);
		
		//EXTENSION DEL ARCHIVO
		panel_ExtensionArchivo.setLayout(null);
		panel_ExtensionArchivo.setBounds(10,105,398,83);
		panel_ExtensionArchivo.setBorder(borde2);
		panelTrabajo.add(panel_ExtensionArchivo);

		label_ExtensionArchivo.setBounds(10, 11, 378, 17);
		label_ExtensionArchivo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		panel_ExtensionArchivo.add(label_ExtensionArchivo);

		textField_ExtensionArchivo.setBounds(10, 40, 378, 32);
		textField_ExtensionArchivo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		panel_ExtensionArchivo.add(textField_ExtensionArchivo);
		
		//RUTA DEL ARCHIVO
		panel_RutaArchivo.setLayout(null);
		panel_RutaArchivo.setBorder(borde2);
		panel_RutaArchivo.setBounds(10, 198, 398, 83);
		panelTrabajo.add(panel_RutaArchivo);
		
		label_RutaArchivo.setBounds(10,11,378,17);
		label_RutaArchivo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		panel_RutaArchivo.add(label_RutaArchivo);
		
		textField_RutaArchivo.setBounds(10, 37, 323, 35);
		textField_RutaArchivo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		panel_RutaArchivo.add(textField_RutaArchivo);
		
		boton_BackSlash.setBounds(336,37,52,35);
		boton_BackSlash.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		boton_BackSlash.setBackground(new Color(220, 220, 220));
		panel_RutaArchivo.add(boton_BackSlash);
		boton_BackSlash.addActionListener((ActionEvent e)->{
			textField_RutaArchivo.setText(textField_RutaArchivo.getText()+"\\");
		});
		boton_EncriptarArchivo.setBackground(UIManager.getColor("Button.light"));
		
		//GENERACION DE LLAVES SIMETRICAS Y ASIMETRICAS
		//INSTANCIAS DE LAS CLASES PARA ENCRIPTACION - DESENCRIPTADO Y FIRMA DIGITAL
        Llaves.generarParLlaves();
		Llaves.generarLlaveAES();
		Cripto.instanciarCipherAES();
		Firma.instanciarFirma();
		
		//BOTONES DE ACCION
		boton_EncriptarArchivo.setBounds(10, 292, 197, 43);
		boton_EncriptarArchivo.setBorder(borde2);
		boton_EncriptarArchivo.setBackground(new Color(220, 220, 220));
		panelTrabajo.add(boton_EncriptarArchivo);
		boton_EncriptarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//SE OBTIENE LA RUTA DEL ARCHIVO A ENCRIPTAR Y SE COMPRUEBA SU EXISTENCIA
				String ruta_archivo = textField_RutaArchivo.getText()+textField_NombreArchivo.getText()+textField_ExtensionArchivo.getText();
				File archivo_A_Encriptar = new File(ruta_archivo);
				if(archivo_A_Encriptar.exists()) {
					try {
						//OBTENER BYTES DEL ARCHIVO ORIGINAL QUE SERA ENCRIPTADO
						FileInputStream inputStream = new FileInputStream(archivo_A_Encriptar);
						byte[] bytesArchivo = new byte[(int) archivo_A_Encriptar.length()];
			            inputStream.read(bytesArchivo);
						inputStream.close();
						
						//ENCRIPTAR ARCHIVO CON CIPHER (LLAVE SIMETRICA ALGORITMO AES) Y OBTENER LOS BYTES DEL ARCHIVO ENCRIPTADO
						byte[] bytes_archivo_encriptado = Cripto.encriptarAES(bytesArchivo);
						
						//GENERAR SALIDA DE LOS BYTES EN LA RUTA DEL ARCHIVO ORIGINAL CON "[ENCRIPTADO - FIRMA]"
			            FileOutputStream outputStream = new FileOutputStream(new File(textField_RutaArchivo.getText()+textField_NombreArchivo.getText()+" [ENCRIPTADO - FIRMA]"+textField_ExtensionArchivo.getText()));
			            outputStream.write(bytes_archivo_encriptado);
			            outputStream.close();
			            
			            //CREAR FIRMA DIGITAL CON LOS BYTES DEL ARCHIVO ENCRIPTADO Y CON LLAVE PRIVADA
			            Firma.crearFirma(bytes_archivo_encriptado);
			            
			            //MOSTRAR MENSAJE DE ESTATUS
			            JOptionPane.showMessageDialog(null, "EXITO: Archivo encriptado - Firma digital generada: " + Firma.obtenerBytesFirma());
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "ERROR: No se pudo desencriptar el archivo");
					}

				}else {
					JOptionPane.showMessageDialog(null, "El archivo no existe");
				}
				
			}
		});
		
		boton_DesencriptarArchivo.setBounds(217, 292, 191, 43);
		boton_DesencriptarArchivo.setBorder(borde2);
		boton_DesencriptarArchivo.setBackground(new Color(220, 220, 220));
		panelTrabajo.add(boton_DesencriptarArchivo);
		boton_DesencriptarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//SE OBTIENE LA RUTA DEL ARCHIVO A DESENCRIPTAR Y SE COMPRUEBA SU EXISTENCIA
				String ruta_archivo = textField_RutaArchivo.getText()+textField_NombreArchivo.getText()+textField_ExtensionArchivo.getText();
				File archivo_A_Desencriptar = new File(ruta_archivo);
				if(archivo_A_Desencriptar.exists()) {
					try {
						//SE LEE EL ARCHIVO A DESENCRIPTAR
						FileInputStream inputStream = new FileInputStream(archivo_A_Desencriptar);
						byte[] bytes_archivo_encriptado = new byte[(int) archivo_A_Desencriptar.length()];
			            inputStream.read(bytes_archivo_encriptado);
			            inputStream.close();
			            
						//VALIDAR FIRMA CON LOS BYTES DEL ARCHIVO ENCRIPTADO ANTES DE DESENCRITPAR
			            Boolean resultado_verificacion_firma = Firma.verificarFirma(bytes_archivo_encriptado);
			            if(resultado_verificacion_firma) {
			            	//DE SER VALIDA LA FIRMA DESENCRIPTAR EL ARCHIVO ENCRIPTADO CON CIPHER (AES)
			            	byte[] bytesArchivoDesencriptado = Cripto.desencriptarAES(bytes_archivo_encriptado);
			            	
				            //GENERAR UN FLUJO DE SALIDA PARA LOS BYTES DEL ARCHIVO ENCRIPTADO
			            	FileOutputStream outputStream = new FileOutputStream(new File(textField_RutaArchivo.getText()+textField_NombreArchivo.getText().replace(" [ENCRIPTADO - FIRMA]", " [ORIGINAL]")+textField_ExtensionArchivo.getText()));
				            outputStream.write(bytesArchivoDesencriptado);
				            outputStream.close();
				            
				            //MOSTRAR MENSAJE DE ESTADO
				            JOptionPane.showMessageDialog(null, "EXITO: Archivo desencriptato - Firma digital valida: " + Firma.obtenerBytesFirmaValida());
				            
			            }else {
							JOptionPane.showMessageDialog(null, "ERROR: La firma digital es invalida.");
			            }
			            
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "ERROR: No se pudo desencriptar el archivo");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "El archivo no existe");
				}

			}
		});
		
	}
	
	//METODO PARA MOSTRAR LA VISTA PRINCIPAL
	public void obtenerInterfazUsuario() {
		marcoPrincipal.setVisible(true);
	}
	
}
