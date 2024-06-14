import java.util.List;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Personagem{
    private String id;
    private String name;
    private List<String> alternate_names;
    private String house;
    private String ancestry;
    private String species; 
    private String patronus;
    private Boolean hogwartsStaff;
    private String hogwartsStudent;
    private String actorName;
    private Boolean alive;
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private Boolean wizard;
        
    public Personagem() {
    }

    public Personagem(String id, String name, List<String> alternate_names, String house, String ancestry, 
        String species, String patronus, Boolean hogwartsStaff, String hogwartsStudent, String actorName, 
        Boolean alive,Date dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour, 
        Boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getAlternate_names() {
        return alternate_names;
    }
    public void setAlternate_names(List<String> alternate_names) {
        this.alternate_names = alternate_names;
    }
    public String getHouse() {
        return house;
    }
    public void setHouse(String house) {
        this.house = house;
    }
    public String getAncestry() {
        return ancestry;
    }
    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getPatronus() {
        return patronus;
    }
    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }
    public Boolean getHogwartsStaff() {
        return hogwartsStaff;
    }
    public void setHogwartsStaff(Boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }
    public String getHogwarstsStudent() {
        return hogwartsStudent;
    }
    public void setHogwarstsStudent(String hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }
    public String getActorName() {
        return actorName;
    }
    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
    public Boolean getAlive() {
        return alive;
    }
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public String getEyeColour() {
        return eyeColour;
    }
    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getHairColour() {
        return hairColour;
    }
    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }
    public Boolean getWizard() {
        return wizard;
    }
    public void setWizard(Boolean wizard) {
        this.wizard = wizard;
    }

    public void ler(){
        
    }

    public void escrever(){
        MyIO.print("["+id+" ## "+name+" ## {");
        for(int i=0; i<alternate_names.size();i++){
            if(i < alternate_names.size()-1){
                MyIO.print(alternate_names.get(i)+",");
            }else{            
                MyIO.print(alternate_names.get(i));
            }
        }
        MyIO.print("} ## "+house+" ## "+ancestry+" ## "
        +species+" ## "+patronus+" ## "+hogwartsStaff+" ## "+hogwartsStudent+" ## "+actorName+" ## "+alive+
        " ## ");
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
        MyIO.print(dataFormatada.format(dateOfBirth));
        MyIO.println(" ## "+yearOfBirth+" ## "+eyeColour+" ## "+gender+" ## "+hairColour+" ## "+wizard+"]");
    }
}

class Celula{
    public Personagem personagem;
    public Celula prox;

    public Celula(){
        this(null);
    }

    public Celula(Personagem personagem){
        this.personagem = personagem;
        this.prox = null;
    }
}


class PilhaFlex{
    private Celula topo;
    private int tamanho;

    public PilhaFlex(){
        topo=null;
        this.tamanho=0;
    }

    public void inserir(Personagem personagem){
        Celula tmp = new Celula(personagem);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
        tamanho++;
    }

    public Personagem remover(){
        if (topo == null) {
			MyIO.println("Erro ao remover!");
		}
		Personagem resp = topo.personagem;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
        tamanho--;
		return resp;
    }

    public void mostrar(){
        int k = tamanho;
        MyIO.println("[ Top ]");
        for(Celula j = topo; j != null; j = j.prox, k--){
            int posicao = tamanho-k;
            MyIO.print("["+posicao+ " ## " +j.personagem.getId()+" ## "+j.personagem.getName()+" ## {");
            for(int i=0; i <+j.personagem.getAlternate_names().size();i++){
                if(i <+j.personagem.getAlternate_names().size()-1){
                    MyIO.print(j.personagem.getAlternate_names().get(i)+",");
                }else{            
                    MyIO.print(j.personagem.getAlternate_names().get(i));
                }
            }
            MyIO.print("} ## "+j.personagem.getHouse()+" ## "+j.personagem.getAncestry()+" ## "+j.personagem.getSpecies()+" ## "+
        j.personagem.getPatronus()+" ## "+j.personagem.getHogwartsStaff()+" ## ");
            if(j.personagem.getHogwarstsStudent().equals("VERDADEIRO")){MyIO.print("true");}else{MyIO.print("false");}
            MyIO.print(" ## "+j.personagem.getActorName()+" ## "+j.personagem.getAlive()+" ## ");
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
            MyIO.print(dataFormatada.format(j.personagem.getDateOfBirth()));
            MyIO.println(" ## "+j.personagem.getYearOfBirth()+" ## "+j.personagem.getEyeColour()+" ## "+j.personagem.getGender()+" ## "+
        j.personagem.getHairColour()+" ## "+j.personagem.getWizard()+"]");
        }
        MyIO.println("[ Bottom ]");
    }

}


public class Q6{
    static private String[] lerLinha(String linha){
        String[] linhaLida = linha.split(";");
        for(int i=0; i<linhaLida.length;i++){
            if(linhaLida[i].equals("[]")){
                linhaLida[i] = "";
            }else{
                linhaLida[i] = linhaLida[i].replace("[","");
                linhaLida[i] = linhaLida[i].replace("]","");
                linhaLida[i] = linhaLida[i].replace("'","");
            }
        }
        return linhaLida;
    }

    static private Personagem[] criacaoDePersonagem(){
        Personagem[] vetorPersonagens = new Personagem[406];
        Arq.openRead("/tmp/characters.csv");
        for(int i=0 ;i<405 ;i++){
            String linhaArquivo = Arq.readLine();
            if(i>0){
                String[] linhaLida = lerLinha(linhaArquivo);
                String id = linhaLida[0];
                String name = linhaLida[1];
                String[] nomes = linhaLida[2].split(",");
                List<String> alternate_names = new ArrayList<>();
                for(int j=0; j<nomes.length;j++){
                    alternate_names.add(nomes[j]);
                }
                String house = linhaLida[3];
                String ancestry = linhaLida[4];
                String species = linhaLida[5];
                String patronus = linhaLida[6];
                Boolean hogwartsStaff = linhaLida[7].equals("VERDADEIRO") ? true : false ;
                String hogwartsStudent = linhaLida[8];
                String actorName = linhaLida[9];
                Boolean alive = linhaLida[10].equals("VERDADEIRO") ? true : false ;
                String data = linhaLida[12];
                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
                Date dateOfBirth = new Date();
                try {
                    dateOfBirth = dataFormatada.parse(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int yearOfBirth = Integer.valueOf(linhaLida[13]);
                String eyeColour = linhaLida[14];
                String gender = linhaLida[15];
                String hairColour = linhaLida[16];
                Boolean wizard = linhaLida[17].equals("VERDADEIRO") ? true : false ;
                Personagem novoPersonagem = new Personagem(id,name,alternate_names,house,ancestry,species,patronus,hogwartsStaff,hogwartsStudent,actorName,alive,dateOfBirth,
                yearOfBirth,eyeColour,gender,hairColour,wizard); 
                vetorPersonagens[i-1] = novoPersonagem;               
            }     
        }
        Arq.close(); 
        return vetorPersonagens;
    }

    static private Personagem[] procuraPorId(Personagem[] personagens, List<String> procura){
        int tamanho = procura.size();
        Personagem[] arrayPersonagens = new Personagem[tamanho];
        for(int i=0;i<tamanho;i++){     
            for(int j=0; j<404;j++){
                String idPersonagem = personagens[j].getId();
                if(idPersonagem.equals(procura.get(i))){
                    arrayPersonagens[i] = personagens[j];
                }
            } 
        }
        return arrayPersonagens;
    }

    static private Personagem procurarID(Personagem[] personagens, String procura){
        for(int i=0;i<personagens.length-1;i++){
            String idPersonagem = personagens[i].getId();
            if(idPersonagem.equals(procura)){
                return personagens[i];
            }
        }
        Personagem tmp = new Personagem();
        return tmp;
    }

    static public void opcoes(String opcao, String Id, PilhaFlex lista, Personagem[] arrayPersonagens){
        switch(opcao){
            case "I": 
                lista.inserir(procurarID(arrayPersonagens, Id));
                break;
            case "R":
                Personagem tempo = lista.remover();
                MyIO.print("(R) ");
                MyIO.println(tempo.getName());
                break;
        }
    }

    public static void main(String[] args){
        int i=0;
        Scanner teclado = new Scanner(System.in);
        Personagem[] personagens = criacaoDePersonagem();
        List<String> procura = new ArrayList<>();
        String leitura = teclado.nextLine();
        procura.add(leitura);
        while(!procura.get(i).equals("FIM")){    
            i++;
            leitura = teclado.nextLine();
            procura.add(leitura);
        }
        Personagem[] arrayPersonagens = procuraPorId(personagens,procura);
        int tamanho = teclado.nextInt();
        teclado.nextLine();
        String opcao;
        PilhaFlex lista = new PilhaFlex();
        for(int j=0;j<arrayPersonagens.length-1;j++){
            lista.inserir(arrayPersonagens[j]);
        }
        opcao = teclado.nextLine(); 
        String[] separado = opcao.split(" ");
        for(int j=0; j<tamanho;j++){
            if(separado[0].equals("R")){
                opcoes(separado[0], " ", lista, personagens);
                if(teclado.hasNextLine()){
                    opcao = teclado.nextLine();
                    separado = opcao.split(" ");
                }
            }else if(separado[0].equals("I")){
                opcoes(separado[0], separado[1], lista, personagens);
                if(teclado.hasNextLine()){
                    opcao = teclado.nextLine();
                    separado = opcao.split(" ");
                }
            }
        }
        lista.mostrar();
        teclado.close();
    }
}