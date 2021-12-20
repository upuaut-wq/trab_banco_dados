package trab2jav;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import trab2jav.modelAluno;


public class controllerAluno {
	modelHead mh;
	
	public controllerAluno() {
		mh = new modelHead();
	}
	
	boolean insert_aluno(modelAluno model) throws IOException {
		read_head();
		Path a = Paths.get("aluno.txt");
		if(mh.qt_free > 0) {
			//Escreve na primeira posição livre
			write_free_space(model);
			mh.qt_free--;
			mh.pos_free.remove(0);
		}else {
			//Escreve no final
			model.id_aluno = mh.qt_total;
			mh.qt_total++;
			String str = convert_model_string(model) + '\n';
			try (BufferedWriter writer = Files.newBufferedWriter(a, StandardOpenOption.APPEND)) {
			    writer.write(str);
			} catch (IOException ioe) {
			    System.err.format("IOException: %s%n", ioe);
			}
		}
		//Rescreve o head
		at_head();
		return true;
		
}
	
 modelAluno busca_aluno(String cpf) throws IOException {
		 BufferedReader file = new BufferedReader(new FileReader("aluno.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;
	        modelAluno md;
	        while ((line = file.readLine()) != null) {
	        	md = convert_string_model(line);
	        	if(  md.cpf.equals(cpf) && md.free == 1) {
	        		return md;
	        	}
	        }   
		return null;
	}
	
	public String convert_model_string(modelAluno model) {
		String str_ret = "";

		str_ret += Integer.toString(model.id_aluno) + ":";
		str_ret += model.nome_aluno + ":";
		str_ret += model.cpf + ":";
		str_ret += model.num + ":";
		str_ret += model.complemento + ":";
		str_ret += Integer.toString(model.id_endereco) + ":";
		str_ret += Integer.toString(model.id_sexo) + ":";
		str_ret += Integer.toString(model.id_email) + ":";
		str_ret += Integer.toString(model.id_telefone) + ":";
		str_ret += Integer.toString(model.id_tipo_ingresso)+ ":";
		str_ret += Integer.toString(model.free);
		//str_ret += '\n';
		return str_ret;
		
	}
	
	modelAluno convert_string_model(String str) {
		int i = 0;
		modelAluno md = new modelAluno();
		String id_aluno = "";
		while(str.charAt(i) != ':') {
			id_aluno += str.charAt(i); 
			i++;
		}
		md.id_aluno = Integer.parseInt(id_aluno);
		i++;
		
		String nome_aluno = "";
		while(str.charAt(i) != ':') {
			nome_aluno += str.charAt(i); 
			i++;
		}
		md.nome_aluno = nome_aluno; 
		i++;
		
		String cpf = "";
		while(str.charAt(i) != ':') {
			cpf += str.charAt(i); 
			i++;
		}
		md.cpf = cpf; 
		i++;
		
		String num = "";
		while(str.charAt(i) != ':') {
			num += str.charAt(i); 
			i++;
		}
		md.num = num; 
		i++;
		
		String complemento = "";
		while(str.charAt(i) != ':') {
			complemento += str.charAt(i); 
			i++;
		}
		md.complemento = complemento; 
		i++;
		
		String id_endereco = "";
		while(str.charAt(i) != ':') {
			id_endereco += str.charAt(i); 
			i++;
		}
		md.id_endereco = Integer.parseInt(id_endereco); 
		i++;
		
		String id_sexo = "";
		while(str.charAt(i) != ':') {
			id_sexo += str.charAt(i); 
			i++;
		}
		md.id_sexo = Integer.parseInt(id_sexo); 
		i++;
		
		String id_email = "";
		while(str.charAt(i) != ':') {
			id_email += str.charAt(i); 
			i++;
		}
		md.id_email = Integer.parseInt(id_email); 
		i++;
		
		String id_telefone = "";
		while(str.charAt(i) != ':') {
			id_telefone += str.charAt(i); 
			i++;
		}
		md.id_telefone = Integer.parseInt(id_telefone); 
		i++;
		
		String id_tipo_ingresso = "";
		while(str.charAt(i) != ':') {
			id_tipo_ingresso += str.charAt(i); 
			i++;
		}
		md.id_tipo_ingresso = Integer.parseInt(id_tipo_ingresso); 
		i++;
		
		String free = "";
		while(i < str.length()) {
			free += str.charAt(i); 
			i++;
		}
		md.free = Integer.parseInt(free); 
		
		return md;
	}
	
	public void read_head() throws IOException {
		Path h = Paths.get("head.txt");
		ArrayList<String> linhasArquivo = (ArrayList<String>) Files.readAllLines(h);
		mh.qt_total = Integer.parseInt(linhasArquivo.get(0));
		mh.qt_free = Integer.parseInt(linhasArquivo.get(1));
		linhasArquivo.remove(0);
		linhasArquivo.remove(0);
		
		for(int i = 0; i < mh.qt_free; i++) {
			mh.pos_free.add(Integer.parseInt(linhasArquivo.get(i)));
		}
	}
	
	
	public void write_free_space(modelAluno model) throws IOException {
		
		// input the file content to the StringBuffer "input"
        BufferedReader file = new BufferedReader(new FileReader("aluno.txt"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        int pos = mh.pos_free.get(0);
        int i = 0;
        while ((line = file.readLine()) != null) {
        	if(i == pos) {
        		model.id_aluno = pos;
                line = convert_model_string(model) + '\n';
                inputBuffer.append(line);
        	}else {
        		inputBuffer.append(line);
                inputBuffer.append('\n');
                
        	}   
        	i++;
        }
        
        file.close();
        
        String inputStr = inputBuffer.toString();
     // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("aluno.txt");
        fileOut.write(inputStr.getBytes());
        fileOut.close();

	}
	
	public void at_head() throws IOException {
		String str = Integer.toString(mh.qt_total)  + '\n';
		str += Integer.toString(mh.qt_free) + '\n';
		
		int max = mh.qt_free;
		for(int i = 0; i < max; i++) {
			str += Integer.toString(mh.pos_free.get(i)) + '\n';
		}
		 FileOutputStream fileOut = new FileOutputStream("head.txt");
	        fileOut.write(str.getBytes());
	        fileOut.close();
		
	}
	
	
public boolean deleta_aluno(String cpf) throws IOException {
		read_head();
		// input the file content to the StringBuffer "input"
        BufferedReader file = new BufferedReader(new FileReader("aluno.txt"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        int i = 0;
        int pos_deleted = -1;
        while ((line = file.readLine()) != null) {
        	modelAluno md = convert_string_model(line);
        	if(md.cpf.equals(cpf) && md.free != 0) {
        		md.free = 0;
        		
        		inputBuffer.append(convert_model_string(md) + '\n');
                pos_deleted = i;
        	}else {
        		inputBuffer.append(line);
                inputBuffer.append('\n');
        	}   
        	i++;
        }
        
        file.close();
        
        String inputStr = inputBuffer.toString();
     // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("aluno.txt");
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        
        //Atualiza topo 
        if(pos_deleted != -1) {
        	mh.pos_free.add(pos_deleted);
        	mh.qt_free++;
        	at_head();
        	return true;
        }
        return false;
	}
	
	
	
	
}
