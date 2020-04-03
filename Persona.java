public class Persona {
    public String nome;
    public String cognome;
    public String citta;
    public String numero;

    public Persona(String nome, String cognome, String citta, String numero) {
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.numero = numero;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+"\n"+"Cognome: "+cognome+"\n"+"Citta: "+citta+"\n"+"Numero: "+numero+"\n---------------";
    }
}


