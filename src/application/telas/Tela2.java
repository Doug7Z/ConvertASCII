package application.telas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Tela2 extends VBox{

	private Tela1 tela1;
	
	public Tela2(Tela1 tela1) {
		
		 Region espacoReservado = new Region();
	     espacoReservado.setMinSize(840, 560);
		
	     TextArea textArea = new TextArea();
	     textArea.setEditable(false);
	     textArea.setMinWidth(840);
	     textArea.setMaxWidth(840);
	     textArea.setMinHeight(560);
	     textArea.setMaxHeight(560);
	     textArea.getStyleClass().add("areaTela2");
	     Font fonteLarguraFixa = Font.font("Courier New", 3); 
	     textArea.setFont(fonteLarguraFixa);

	    Scale escala = new Scale(1, 1); 
	    textArea.getTransforms().add(escala);

	    
		HBox menuTela2 = new HBox();
        menuTela2.getStyleClass().add("Menu2");

        
        Button btnTela2 = new Button("Gerar Imagem");
        btnTela2.getStyleClass().add("btnTela12Gerar");
        
        Button btnSalvarTela2 = new Button("Salvar Imagem");
        btnSalvarTela2.getStyleClass().add("btnTela12Salvar");
        
        
        
        //*************************Botão Gerar*******************************//
        btnTela2.addEventHandler(ActionEvent.ACTION, event ->{
        	
        try {
        	this.tela1 = tela1;
			String imageUrl = tela1.getUrlImagemSelecionada();
			URI uri = new URI(imageUrl);
			String caminhoDoArquivo = uri.getPath();
			BufferedImage image = ImageIO.read(new File(caminhoDoArquivo));
			
			String asciiChars = " ..¤.‹=-:. ";
			int outputWidth = 500;
			
			int newHeight = (int) ((double) outputWidth / image.getWidth() * image.getHeight() * 0.5);
	        BufferedImage resizedImage = resizeImage(image, outputWidth, newHeight);
			
	        // Mapear pixels para caracteres ASCII
            StringBuilder asciiImage = new StringBuilder();
			
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < outputWidth; x++) {
                    int rgb = resizedImage.getRGB(x, y);
                    int grayscale = (int) (0.2126 * ((rgb >> 16) & 0xFF) + 0.7152 * ((rgb >> 8) & 0xFF) + 0.0722 * (rgb & 0xFF));
                    int index = (int) (grayscale / (256.0 / asciiChars.length()));

                    asciiImage.append(asciiChars.charAt(index));
                }
             // Adiciona quebra de linha ao final de cada linha
                asciiImage.append("\n");  
               
                carregarConteudoDoArquivo(asciiImage.toString(), textArea);
                
//              getChildren().remove(espacoReservado);
                getChildren().clear();
                getChildren().add(0,textArea);
                getChildren().addAll(menuTela2,btnTela2,btnSalvarTela2);
                
            }
        	
		} catch (Exception e) {
			 e.printStackTrace();
		}

        
        });
        //***********************************************//

        
        
        //*******************Botão Salvar********************************//
        
        btnSalvarTela2.addEventHandler(ActionEvent.ACTION, event -> {
        	
        	FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Salvar txt");
        	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos de Texto (*.txt)", "*.txt"));

        	File arquivoEscolhido = fileChooser.showSaveDialog((Stage)getScene().getWindow());
        	if (arquivoEscolhido != null) {
                salvarTextoComoArquivo(arquivoEscolhido, textArea.getText());
            }
        	
        });
        //*****************************************************************//
        

        getChildren().addAll(espacoReservado,menuTela2,btnTela2,btnSalvarTela2);
	
	}
	
	
	
	 private void carregarConteudoDoArquivo(String arquivoImagem, TextArea textArea) {
		 textArea.setText(arquivoImagem);
	    }
	    
	 
	 private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
	        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	        resizedImage.createGraphics().drawImage(originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
	        return resizedImage;
	    }
	   
	 private void salvarTextoComoArquivo (File arquivo , String conteudo) {
		 try (PrintWriter writer = new PrintWriter(arquivo)) {
	            writer.write(conteudo);
	            System.out.println("Conteúdo salvo com sucesso em: " + arquivo.getAbsolutePath());
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
	        }
	 }
	 
}
