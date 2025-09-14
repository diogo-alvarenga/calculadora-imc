import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProjetoCalculadoraIMC extends Application{

	@Override
	public void start(Stage palco){
		String caminhoImagem = "file:///C:/Projetos/Fase1/imc.png";

		Image imagem = new Image(caminhoImagem);
		ImageView imageView = new ImageView(imagem);

		imageView.setFitWidth(330);//largura desejada em pixels
		imageView.setFitHeight(100); //altura desejada em pixels

		//Etiquetas para campos de entrada
		Label etiquetaPeso = new Label("Peso");
		Label etiquetaAltura = new Label("Altura");

		//Campos de texto para entrada de dados
		
		TextField campoPeso = new TextField();
		//texto no fundo do textFIeld
		campoPeso.setPromptText("Peso em KG");
		
		TextField campoAltura = new TextField();
		campoAltura.setPromptText("Altura em metros");

		//Label para mostrar o resultado do imc
		Label etiquetaResultado = new Label();
		Label mensagemAjuda = new Label();

		//Botão para calcular o imc
		Button botaoCalcular = new Button("Calcular IMC");

		botaoCalcular.setOnAction(e -> {
			try{
				//o digitado no campo é string, assim  eu pego como string
				//e converto em double
				double peso = Double.parseDouble(campoPeso.getText().replace(',','.'));
				double altura = Double.parseDouble(campoAltura.getText().replace(',','.'));

				if(altura <=0 || peso <=0){
					etiquetaResultado.setText("A altura e o peso devem ser maiores que 0");

				}else{
				double imc = peso / (altura*altura);
				etiquetaResultado.setText(String.format("Seu IMC é: %.2f",imc).replace(',','.'));

					if(imc< 17){
						mensagemAjuda.setText("Muito abaixo do peso");
					}else if(imc >= 17 && imc <=18.49){
						mensagemAjuda.setText("Abaixo do peso");
					}else if(imc >= 18.5 && imc <=24.99){
						mensagemAjuda.setText("Peso normal");
					}else if(imc >= 25 && imc <= 29.99){
						mensagemAjuda.setText("Acima do peso");
					}else if(imc >= 30 && imc <=34.99){
						mensagemAjuda.setText("Obesidade I");
					}else if(imc>=35 && imc <=39.99){
						mensagemAjuda.setText("Obesidade II (severa)");
					}else{
						mensagemAjuda.setText("Obesidade III (morbida)");
					}	
				}
			}catch(NumberFormatException ex){
				etiquetaResultado.setText("Por favor, insira numeros validos para peso e altura.");
				mensagemAjuda.setText("");
			}

		});

		//Layout vertical
		//o 10 no argumento é o espaçamento entre eles
		VBox layout = new VBox (10,imageView, etiquetaPeso, campoPeso,etiquetaAltura,campoAltura, 
		botaoCalcular,etiquetaResultado,mensagemAjuda);

		layout.setPadding(new Insets(10));
		layout.setAlignment(Pos.CENTER);

		//Cena e palco
		Scene cena = new Scene(layout,350,350);
		palco.setTitle("Calculadora de IMC");

		palco.setScene(cena);
		palco.show();

	}

	public static void main(String[] args){
		launch(args);
	}
}
