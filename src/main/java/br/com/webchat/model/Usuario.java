package br.com.webchat.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Size(max = 255)
    @Column(name = "apelido")
    private String apelido;
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 200)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 255)
    @Column(name = "frase")
    private String frase;
    @OneToMany(mappedBy = "toUser", fetch = FetchType.LAZY)
    private List<Message> messageList;
    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private List<Message> messageList1;
    @Transient
    private String data;

    public Usuario(String nome, String apelido, String email, Date dataNascimento, String cidade, String frase) {

        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cidade = cidade;
        this.frase = frase;

    }

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
      
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageList1() {
        return messageList1;
    }

    public void setMessageList1(List<Message> messageList1) {
        this.messageList1 = messageList1;
    }

    public String getData() {
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = d.format(dataNascimento);
            System.out.println(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.webchat.model.User[ id=" + id + " ]";
    }

}
