package application.telas;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Tela1 extends VBox{
    
	
	private String urlImagemSelecionada;
	
	public Tela1() {
		

        Region espacoReservado = new Region();
        espacoReservado.setMinSize(420, 300);
        
        HBox menuTela1 = new HBox();     
        menuTela1.getStyleClass().add("Menu1");
    
        Button btnTela1 = new Button("Abrir imagem");
        btnTela1.getStyleClass().add("btnTela1Abrir");
        
        
        //**************************Botão Abrir************************************//
        
        btnTela1.addEventHandler(ActionEvent.ACTION, event ->{
        	
        	FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Selecione a imagem");
        	
        	File arquivoSelecionado = fileChooser.showOpenDialog((Stage)getScene().getWindow());
        	        	
			if(arquivoSelecionado != null) {
				
				urlImagemSelecionada = arquivoSelecionado.toURI().toString();
				setUrlImagemSelecionada(urlImagemSelecionada);
				
				String nomeArquivo = arquivoSelecionado.getAbsolutePath();
//		        Image novaImagem = new Image("file:" + nomeArquivo);
		        Image novaImagem = new Image(nomeArquivo);
		        
		        ImageView imageView = new ImageView(novaImagem);
		        
		        imageView.setImage(novaImagem);
		        imageView.setFitWidth(420);
		        imageView.setFitHeight(300);
		        imageView.setPreserveRatio(true);
		        
		        imageView.getStyleClass().add("imageView1");
		        
		        
		        if (getChildren().contains(espacoReservado)) {
                    getChildren().remove(espacoReservado);
                } else {
                    // Se já existe uma ImageView , faz a substituição pela imagem nova
                    getChildren().removeIf(child -> child instanceof ImageView);
                }

		       
                getChildren().add(0, imageView);
		        

			}       	
        });

        
        getChildren().addAll(espacoReservado,btnTela1);

    
    }
	
	public String getUrlImagemSelecionada() {
		return urlImagemSelecionada;
	}
	
	public void setUrlImagemSelecionada(String url) {
	    this.urlImagemSelecionada = url;
//	    System.out.println("URL definida em Tela1: " + url);
	}
    
}
