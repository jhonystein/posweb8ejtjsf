package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.Projetor;
import modelo.Reserva;
import modelo.Sala;
import modelo.Usuario;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import util.Config;

@SessionScoped
@ManagedBean(name="manipulacaoBancoUC")
public class ManipulacaoBancoUC {
	
	public void criarBancoDados() throws Exception{
		
		// Trecho exemplificando uso de JDBC
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
		Statement st = con.createStatement();
		st.execute("drop database if exists "+Config.BANCODADOS);
		st.execute("create database "+Config.BANCODADOS);
		st.close();
		con.close();

		// Trecho que usa especificidades do Hibernate
		AnnotationConfiguration an = new AnnotationConfiguration();
		an.addAnnotatedClass(Usuario.class);
		an.addAnnotatedClass(Sala.class);
		an.addAnnotatedClass(Reserva.class);
		an.addAnnotatedClass(Projetor.class);
		// cada classe a ser criada uma tabela, deve ser colocada aqui e no arquivo persistence.xml

		an.configure();
		
		new SchemaExport(an).create(true, true);

		UsuarioUC.inicializarUsuario();
		
	}

}
