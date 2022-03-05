package calculadoragui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Interfaz implements ActionListener{

    JTextField resultados, cuentas;
    Panel pantalla, botonesOperacionesMemoria, botonesOperacionesAritmeticas;
    JPanel botones, botonesNumeros;
    JButton mc, mr, ms, mMas, mMenos, numeros[], operaciones[];
    String oper[]={"R", "C", "+", "/", "-" ,"*", "="},  ax="";
    float numero1, numero2, numeroResultado, numeroEnMemoria;//variables para las operaciones
    int tipoOperacion; //para controlar el tipo de operacion que se realiza
    boolean escribirNuevoNumero=false;//control sobre escribir un nuevo numero despues de alguna operacion cambia a true cuando se ha realizado una operacion

    public Interfaz(){

        JFrame calculadora = new JFrame("Calculator");
        calculadora.setLayout(new BorderLayout(4, 4));

        norte();
        sur();

        calculadora.add(pantalla, BorderLayout.NORTH);
        calculadora.add(botones, BorderLayout.CENTER);

        calculadora.setLocation(100, 80);
        calculadora.setResizable(false);
        calculadora.setVisible(true);
        calculadora.setSize(300, 380);
        calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void norte(){

        pantalla = new Panel(null);        

        resultados = new JTextField("");
        cuentas = new JTextField("0");

        resultados.setHorizontalAlignment(JTextField.RIGHT); 
        cuentas.setHorizontalAlignment(JTextField.RIGHT); 

        //Quitar bordes a los campos de texto
        resultados.setBorder(BorderFactory.createLineBorder(Color.white));
        cuentas.setBorder(BorderFactory.createLineBorder(Color.white));

        //desabilitando los campos de texto
        resultados.setEditable(false);
        cuentas.setEditable(false);

        resultados.setBackground(Color.white);
        cuentas.setBackground(Color.white);

        pantalla.add(resultados); pantalla.add(cuentas);

        resultados.setBounds(35, 10, 200, 15);
        cuentas.setBounds(35, 25, 200, 30);

        pantalla.setSize(270, 47);
        pantalla.setVisible(true);

    }

    public void sur(){

        botones = new JPanel(new BorderLayout(6, 50));
        botones.setLayout(new BorderLayout(4, 4));

        botMem();
        botNum();
        botOpe();

        botones.add(botonesOperacionesMemoria, BorderLayout.NORTH);  
        botones.add(botonesNumeros, BorderLayout.CENTER); 
        botones.add(botonesOperacionesAritmeticas, BorderLayout.EAST); 

        botones.setSize(270, 330);
    }

    public void botMem(){

        botonesOperacionesMemoria = new Panel(null);

        mc = new JButton("MC");  mr = new JButton("MR");
        ms = new JButton("MS"); mMas = new JButton("M+");
        mMenos = new JButton("M-");

        mc.setFont(new Font("Arial", Font.BOLD, 11));
        mr.setFont(new Font("Arial", Font.BOLD, 11));
        ms.setFont(new Font("Arial", Font.BOLD, 11));
        mMas.setFont(new Font("Arial", Font.BOLD, 11));
        mMenos.setFont(new Font("Arial", Font.BOLD, 11));

        mc.setMargin(new Insets(1, 1, 1, 1)); mr.setMargin(new Insets(1, 1, 1, 1));
        ms.setMargin(new Insets(1, 1, 1, 1)); mMas.setMargin(new Insets(1, 1, 1, 1));
        mMenos.setMargin(new Insets(1, 1, 1, 1)); 

        mc.setBounds(35, 0, 33, 33); mr.setBounds(78, 0, 33, 33); ms.setBounds(121, 0, 33, 33);
        mMas.setBounds(164, 0, 33, 33); mMenos.setBounds(207, 0, 33, 33);

        botonesOperacionesMemoria.add(mc); botonesOperacionesMemoria.add(mr); botonesOperacionesMemoria.add(ms); botonesOperacionesMemoria.add(mMas); botonesOperacionesMemoria.add(mMenos);

        mc.addActionListener(this); mr.addActionListener(this); ms.addActionListener(this);
        mMas.addActionListener(this); mMenos.addActionListener(this);

        botonesOperacionesMemoria.setSize(270, 45);
        botonesOperacionesMemoria.setVisible(true);        
    }

    public void botNum(){

        botonesNumeros = new JPanel(null);

        int nx3=121, nx2=121, nx1=121, n3y=0, numero2y=43, numero1y=86;
        numeros = new JButton[11];

        //*****************************************
        //bloque para crear los botones, añadirlos y asignar numeros
        for (int i=0; i<=10; i++){

            if(i<=9){
                numeros[i] = new JButton(""+i);
                botonesNumeros.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);  
            }
            else{
                numeros[i] = new JButton(".");
                botonesNumeros.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            }
        }

        //******************************************
        //bloque para posicionar botones
        for(int i=10; i>=0; i--){

            if(i==10){
                numeros[i].setBounds(121, 129, 35, 35);
            }
            else{
                if(i<=9 && i>=7){
                    numeros[i].setBounds(nx3, n3y, 35, 35);
                    nx3-=43;
                }
                else if(i<=6 && i>=4){   
                    n3y+=43;                    
                    numeros[i].setBounds(nx2, numero2y, 35, 35);
                    nx2-=43;
                }
                else if(i<=3 && i>=1){
                    n3y+=43;                    
                    numeros[i].setBounds(nx1, numero1y, 35, 35);
                    nx1-=43;
                }
                else if(i==0){
                    numeros[i].setBounds(35, 129, 78, 35);                    
                }
            }                
        }

        botonesNumeros.setSize(170, 150);
        botonesNumeros.setVisible(true);
    }

    public void botOpe(){

        botonesOperacionesAritmeticas = new Panel(null);

        int c=0, x=0, y=0;

        operaciones = new JButton[7];

        for(int i=0; i<=6; i++){
            if(c<=1){

                operaciones[i] = new JButton(oper[i]);
                botonesOperacionesAritmeticas.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;               
            }
            else{
                if(i==6){
                    x=0; y+=43;
                    operaciones[i] = new JButton(oper[i]);
                    botonesOperacionesAritmeticas.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 65, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;
                }
                else{
                    c=0;
                    x=0; y+=43;
                    operaciones[i] = new JButton(oper[i]);
                    botonesOperacionesAritmeticas.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;   
                }                             
            }                

        }

        botonesOperacionesAritmeticas.setVisible(true);
        botonesOperacionesAritmeticas.setSize(120, 200);
    }

    public boolean isN(String ax){

        try{
            Integer.parseInt(ax);
            return true;
        }catch(NumberFormatException e){
            return false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(isN(e.getActionCommand())){ //cuando se oprimen numeros

            apretarNumero(e);            
        }
        else{//cuando se oprime el resto de botones

            if(e.getActionCommand().equals("R") ){
                raizCuadrada(); 
            }
            if(e.getActionCommand().equals("C") ){ //para reiniciar valores y limpiar pantalla
                resetearValores();
            }   
            if(e.getActionCommand().equals("MC")){//para limpiar la memoria de la calculadora
                resetearMemoria();
            }
            if(e.getActionCommand().equals("MR")){//para mostrar valor almacenado en la memoria
                mostrarValorMemoria();
            }if(e.getActionCommand().equals("MS")){//guardar un valor en la memoria
                guardarValorEnMemoria();
            }
            if(e.getActionCommand().equals("M+")){//sumar valor de la pantalla con el valor de la memoria
            	sumarPantallaMemoria();
            }
            if(e.getActionCommand().equals("M-")){//restar valor de la pantalla con el valor de la memoria
            	restarPantallaMemoria();
            }    
            if(e.getActionCommand().equals(".")){//usar el punto para los decimales
                ponerDecimal();
            }
            if(e.getActionCommand().equals("+") ){//boton suma
                prepararSuma();                     
            }
            if(e.getActionCommand().equals("-") ){//cuando se decide restar
                prepararResta();                    
            }
            if(e.getActionCommand().equals("*") ){//cuando se decide multiplicar
                prepararMultiplicacion();                      
            }
            if(e.getActionCommand().equals("/") ){//cuando se decide dividir
                prepararDivision();                      
            }
            if(e.getActionCommand().equals("=") && !cuentas.getText().equals("")){
            	operar();
            }
        }        
    }

	private void operar() {
		escribirNuevoNumero = true;
		if(tipoOperacion==1){//operacion para la suma
		    tipoOperacion = 0;
		    ax="";
		    ax+=resultados.getText() + cuentas.getText();
		    resultados.setText(ax);
		    numero2 = Float.parseFloat(cuentas.getText());
		    numeroResultado=numero1+numero2;
		    cuentas.setText(String.valueOf(numeroResultado));
		}
		else if(tipoOperacion==2){ //operacion para la resta
		    tipoOperacion = 0;
		    ax="";
		    ax+=resultados.getText()+cuentas.getText();
		    resultados.setText(ax);
		    numero2 = Float.parseFloat(cuentas.getText());
		    numeroResultado=numero1-numero2;
		    cuentas.setText(String.valueOf(numeroResultado));
		}
		if(tipoOperacion==3){ //operacion para la multiplicacion
		    tipoOperacion = 0;
		    ax="";
		    ax+=resultados.getText()+cuentas.getText();
		    resultados.setText(ax);
		    numero2 = Float.parseFloat(cuentas.getText());
		    numeroResultado=numero1*numero2;
		    cuentas.setText(String.valueOf(numeroResultado));
		}
		if(tipoOperacion==4){ //operacion para la multiplicacion
		    if(Float.parseFloat(cuentas.getText())!=0){
		        tipoOperacion = 0;
		        ax="";
		        ax+=resultados.getText()+cuentas.getText();
		        resultados.setText(ax);
		        numero2 = Float.parseFloat(cuentas.getText());
		        numeroResultado=numero1/numero2;
		        cuentas.setText(String.valueOf(numeroResultado));
		    }
		    
		}
	}

	private void prepararDivision() {
		numeros[10].setEnabled(true);
		ax="";
		if(tipoOperacion==4){

		}else if(tipoOperacion==0){//validacion para no chocar con otras operaciones
		    if(resultados.getText().equals("")){
		        numero1 = Float.parseFloat(cuentas.getText());                    
		        ax += resultados.getText()+cuentas.getText();
		        resultados.setText(ax+" / ");
		        cuentas.setText("");
		        tipoOperacion = 4;
		    }
		    else{
		        if(!escribirNuevoNumero){//validacion para nueva operacion
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += cuentas.getText();
		            resultados.setText(ax+" / ");
		            cuentas.setText("");
		            tipoOperacion = 4;
		        }
		        else{//usar otras operaciones con la suma
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += resultados.getText();
		            resultados.setText(ax+" / ");
		            cuentas.setText("");
		            tipoOperacion = 4;
		        }
		    }
		}
	}

	private void prepararMultiplicacion() {
		numeros[10].setEnabled(true);
		ax="";
		if(tipoOperacion==3){

		}else if(tipoOperacion==0){//validacion para no chocar con otras operaciones
		    if(resultados.getText().equals("")){
		        numero1 = Float.parseFloat(cuentas.getText());                    
		        ax += resultados.getText()+cuentas.getText();
		        resultados.setText(ax+" * ");
		        cuentas.setText("");
		        tipoOperacion = 3;
		    }
		    else{
		        if(!escribirNuevoNumero){//validacion para nueva operacion
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += cuentas.getText();
		            resultados.setText(ax+" * ");
		            cuentas.setText("");
		            tipoOperacion = 3;
		        }
		        else{//usar otras operaciones con la suma
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += resultados.getText();
		            resultados.setText(ax+" * ");
		            cuentas.setText("");
		            tipoOperacion = 3;
		        }
		    }
		}
	}

	private void prepararResta() {
		numeros[10].setEnabled(true);
		ax="";
		if(tipoOperacion==2){

		}else if(tipoOperacion==0){//validacion para no chocar con otras operaciones
		    if(resultados.getText().equals("")){
		        numero1 = Float.parseFloat(cuentas.getText());                    
		        ax += resultados.getText()+ cuentas.getText();
		        resultados.setText(ax+" - ");
		        cuentas.setText("");
		        tipoOperacion = 2;
		    }
		    else{
		        if(!escribirNuevoNumero){//validacion para nueva operacion
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += cuentas.getText();
		            resultados.setText(ax+" - ");
		            cuentas.setText("");
		            tipoOperacion = 2;
		        }
		        else{//usar otras operaciones con la suma
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += resultados.getText();
		            resultados.setText(ax+" - ");
		            cuentas.setText("");
		            tipoOperacion = 2;
		        }
		    }
		}
	}

	private void prepararSuma() {
		numeros[10].setEnabled(true);
		ax="";
		if(tipoOperacion==1){

		}else if(tipoOperacion==0 ){//validacion para no chocar con otras operaciones
		        if(resultados.getText().equals("") ){
		            numero1 = Float.parseFloat(cuentas.getText());                    
		            ax += resultados.getText()+cuentas.getText();
		            resultados.setText(ax+" + ");
		            cuentas.setText("");
		            tipoOperacion = 1;
		        }
		        else {
		            if(!escribirNuevoNumero){//validacion para nueva operacion
		                numero1 = Float.parseFloat(cuentas.getText());                    
		                ax += cuentas.getText();
		                resultados.setText(ax+" + ");
		                cuentas.setText("");
		                tipoOperacion = 1;
		            }
		            else{//usar otras operaciones con la suma
		                numero1 = Float.parseFloat(cuentas.getText());                    
		                ax += resultados.getText();
		                resultados.setText(ax+" + ");
		                cuentas.setText("");
		                tipoOperacion = 1;
		            }
		        }
		    }
	}

	private void ponerDecimal() {
		ax="";
		if(numeros[10].isEnabled()){
		    numeros[10].setEnabled(false);
		    ax = cuentas.getText() +".";
		    cuentas.setText(ax);
		}
	}

	private void restarPantallaMemoria() {
		numeroEnMemoria -= Float.parseFloat(cuentas.getText());
	}

	private void sumarPantallaMemoria() {
		numeroEnMemoria += Float.parseFloat(cuentas.getText());
	}

	private void guardarValorEnMemoria() {
		ms.setForeground(Color.red);
        numeroEnMemoria = Float.parseFloat(cuentas.getText());
	}

	private void mostrarValorMemoria() {
		resultados.setText("");
        cuentas.setText(String.valueOf(numeroEnMemoria));
	}

	private void resetearMemoria() {
		ms.setForeground(Color.black);
        resultados.setText(""); cuentas.setText("0");
        numeroEnMemoria=0;
	}

	private void resetearValores() {
		tipoOperacion=0; 
		numero1 = 0; 
		numero2 =0;
		numeroResultado=0; 
		resultados.setText(""); 
		cuentas.setText("0"); 
		ax="";
	}

	private void raizCuadrada() {
		resultados.setText("");
        Float a = Float.parseFloat(cuentas.getText());
        cuentas.setText(""+Math.sqrt(a));
	}

	private void apretarNumero(ActionEvent e) {
		if(resultados.getText().equals("")){
            ax += e.getActionCommand();
            cuentas.setText(ax);
        }
        else{
            if(tipoOperacion==0){
                if(escribirNuevoNumero){
                    ax=""; 

                    resultados.setText(cuentas.getText());                        
                    ax += e.getActionCommand();
                    cuentas.setText(ax);    
                    escribirNuevoNumero = false;
                }
                else{
                    ax="";
                    ax += cuentas.getText()+e.getActionCommand();
                    cuentas.setText(ax);
                }                
            }else{
                ax="";
                ax += cuentas.getText()+e.getActionCommand();
                cuentas.setText(ax);
            }
        }
	}     
	
	
}