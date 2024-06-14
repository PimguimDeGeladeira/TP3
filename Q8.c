#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

typedef struct Personagens
{
    char id[101];
    char name[101];
    char alternate_names[10001];
    char house[101];
    char ancestry[101];
    char species[101]; 
    char patronus[101];
    int hogwartsStaff;
    char hogwartsStudent[101];
    char actorName[101];
    int alive;
    char dateOfBirth[11];
    int yearOfBirth;
    char eyeColour[101];
    char gender[101];
    char hairColour[101];
    int wizard;
}Personagens;

char** split(char* linha, char delim) {
    strcat(linha,";");
    int num_delimiters = 0;
    int len = strlen(linha);
    
    // Conta o número de delimitadores na string
    for (int i = 0; i < len; i++) {
        if (linha[i] == delim) {
            num_delimiters++;
        }
    }

    // Aloca memória para o array de strings
    char** atributos;
    atributos = malloc((num_delimiters + 1) * sizeof(char*));
    if (atributos == NULL) {
        printf("Erro ao alocar memória.\n");
        exit(1);
    }

    // Inicializa o array de strings
    for (int i = 0; i <= num_delimiters; i++) {
        atributos[i] = (char*)malloc((len + 1) * sizeof(char));
        if (atributos[i] == NULL) {
            printf("Erro ao alocar memória.\n");
            exit(1);
        }
    }

    // Realiza a divisão da string
    int atributo_index = 0;
    int start = 0;
    int i=0;
    for (int i = 0; i <= len; i++) {
        if (linha[i] == delim || linha[i] == '\0') {
            strncpy(atributos[atributo_index], linha+start, i - start);
            atributos[atributo_index][i - start] = '\0'; // Adiciona o terminador nulo
            start = i + 1;
            atributo_index++;
        }
    }
    return atributos;
}

Personagens *ptrteste(){
    FILE *ponteiroArquivo = fopen("characters.csv","r");
    const int totalPersonagens = 405;
    Personagens *listaPersonagens = malloc(totalPersonagens * sizeof(Personagens));
    char lixo[101], booleanoHogwartsStaff[101], booleanoAlive[101], dataNascimento[101], mago[101], estudanteDeHogwarts[101],string[1001];
    if(ponteiroArquivo != NULL){ 
        for(int i=0; i<405; i++){
            if(i==0){
                char strlixo[201];
                fscanf(ponteiroArquivo," %[^\n]",strlixo);
            }
            if(i>0){
                fscanf(ponteiroArquivo," %[^\n]",string);
                char **atributos = split(string,';');
                strcpy(listaPersonagens[i].id,atributos[0]);
                strcpy(listaPersonagens[i].name,atributos[1]);
                strcpy(listaPersonagens[i].alternate_names,atributos[2]); 
                strcpy(listaPersonagens[i].house,atributos[3]);
                strcpy(listaPersonagens[i].ancestry,atributos[4]);
                strcpy(listaPersonagens[i].species,atributos[5]); 
                strcpy(listaPersonagens[i].patronus,atributos[6]); 
                strcpy(booleanoHogwartsStaff,atributos[7]);
                strcpy(estudanteDeHogwarts,atributos[8]);
                strcpy(listaPersonagens[i].actorName,atributos[9]);
                strcpy(booleanoAlive,atributos[10]);
                strcpy(lixo,atributos[11]);
                strcpy(listaPersonagens[i].dateOfBirth,atributos[12]);
                strcpy(dataNascimento,atributos[13]);          
                strcpy(listaPersonagens[i].eyeColour,atributos[14]);
                strcpy(listaPersonagens[i].gender,atributos[15]);
                strcpy(listaPersonagens[i].hairColour,atributos[16]);
                strcpy(mago,atributos[18]);
                if(strcmp(booleanoHogwartsStaff,"FALSO") == 0){
                    listaPersonagens[i].hogwartsStaff = 0;
                }else{
                    listaPersonagens[i].hogwartsStaff = 1;
                }
                if(strcmp(estudanteDeHogwarts,"FALSO") == 0){
                    strcpy(listaPersonagens[i].hogwartsStudent,"false");
                }else{
                    strcpy(listaPersonagens[i].hogwartsStudent,"true");    
                }
                if(strcmp(booleanoAlive,"FALSO") == 0){
                    listaPersonagens[i].alive = 0;
                }else{
                    listaPersonagens[i].alive = 1;
                }
                listaPersonagens[i].yearOfBirth = atoi(dataNascimento);
                if(strcmp(mago,"FALSO") == 0){
                    listaPersonagens[i].wizard = 0;
                }else{
                    listaPersonagens[i].wizard = 1;
                }
            }
        }
    }
    fclose(ponteiroArquivo);
    return listaPersonagens;
}

void remove_caracteres(char *str) {
    int i, j;
    int len = strlen(str);

    // Percorre a string e remove os caracteres específicos
    for (i = 0; i < len; i++) {
        if (str[i] == '[' || str[i] == ']' || str[i] == '\'') {
            for (j = i; j < len; j++) {
                str[j] = str[j + 1];
            }
            len--; // Decrementa o comprimento da string após remover o caractere
            i--;   // Mantém a mesma posição de i para verificar o próximo caractere
        }
    }
}

void imprimirPersonagem(Personagens *ptrPersonagens, int index){
        printf("[%s ##",ptrPersonagens[index].id);
        printf(" %s ##",ptrPersonagens[index].name);
        remove_caracteres(ptrPersonagens[index].alternate_names);
        printf(" {%s} ##",ptrPersonagens[index].alternate_names);
        printf(" %s ##",ptrPersonagens[index].house);
        printf(" %s ##",ptrPersonagens[index].ancestry);
        printf(" %s ##",ptrPersonagens[index].species);
        printf(" %s ##",ptrPersonagens[index].patronus);
        printf(" ##",ptrPersonagens[index].hogwartsStaff == 0 ? printf(" false"):printf(" true"));
        printf(" %s ##",ptrPersonagens[index].hogwartsStudent);
        printf(" %s ##",ptrPersonagens[index].actorName);
        printf(" ##",ptrPersonagens[index].alive == 0 ? printf(" false"):printf(" true"));
        printf(" %s ##",ptrPersonagens[index].dateOfBirth);
        printf(" %d ##",ptrPersonagens[index].yearOfBirth);
        printf(" %s ##",ptrPersonagens[index].eyeColour);
        printf(" %s ##",ptrPersonagens[index].gender);
        printf(" %s ##",ptrPersonagens[index].hairColour);
        printf("]\n",ptrPersonagens[index].wizard == 0 ? printf(" false"):printf(" true"));   
}

Personagens *procuraPorId(Personagens *personagens, char procura[][101], int tamanho){
    Personagens *ptr = malloc(tamanho * sizeof(Personagens));
    for(int i=0;i< tamanho;i++){
        for(int j=1;j<405;j++){
            char idPersonagem[1001]; 
            strcpy(idPersonagem, personagens[j].id); 
            if(strcmp(idPersonagem,procura[i]) == 0){
                ptr[i] = personagens[j];
            }
        }
    }
    return ptr;
}
typedef struct CelulaDupla{
    Personagens personagem;
    struct CelulaDupla *prox, *ant;
}CelulaDupla;

CelulaDupla* new_celula_dupla(Personagens personagem){
    CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    temp->personagem = personagem;
    temp->ant = NULL;
    temp->prox = NULL;
    return temp;
}

typedef struct ListaDupla{
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;

ListaDupla new_lista_dupla(){
    ListaDupla temp;
    Personagens tmp;
    temp.primeiro = temp.ultimo = new_celula_dupla(tmp);
    temp.size = 0;
    return temp;
}

void inserir(ListaDupla *l, Personagens personagem){    
    l->ultimo->prox = new_celula_dupla(personagem);
    l->ultimo->prox->ant = l->ultimo;
    l->ultimo = l->ultimo->prox;
    l->size++;
}

void mostrar (ListaDupla *l){
    CelulaDupla *i;  
    for(i = l->primeiro->prox; i!=NULL ;i = i->prox){
        printf("[ %s ##", i->personagem.id);
        printf(" %s ##",i-> personagem.name);
        remove_caracteres(i-> personagem.alternate_names);
        printf(" {%s} ##",i-> personagem.alternate_names);
        printf(" %s ##",i-> personagem.house);
        printf(" %s ##",i-> personagem.ancestry);
        printf(" %s ##",i-> personagem.species);
        printf(" %s ##",i-> personagem.patronus);
        printf(" ##",i-> personagem.hogwartsStaff == 0 ? printf(" false"):printf(" true"));
        printf(" %s ##",i-> personagem.hogwartsStudent);
        printf(" %s ##",i-> personagem.actorName);
        printf(" ##",i-> personagem.alive == 0 ? printf(" false"):printf(" true"));
        printf(" %s ##",i-> personagem.dateOfBirth);
        printf(" %d ##",i-> personagem.yearOfBirth);
        printf(" %s ##",i-> personagem.eyeColour);
        printf(" %s ##",i-> personagem.gender);
        printf(" %s ##",i-> personagem.hairColour);
        printf("]\n",i-> personagem.wizard == 0 ? printf(" false"):printf(" true"));
    }
}

Personagens procuraPersonagem(Personagens *personagens, char ID[51]){
    Personagens ptr;
    for(int i=0;i<404;i++){
        if(strcmp(ID, personagens[i].id) ==0){
            ptr = personagens[i];
            i=405;
        }
    }
    return ptr;
}
int compareHouse(Personagens p1, Personagens p2){
    int comparacao = strcmp(p1.house,p2.house); 
    if(comparacao == 0){
        //comparacoes++;
        return strcmp(p1.name,p2.name);
    }
    return comparacao;
}

void swap(Personagens *i, Personagens *j) {
   Personagens temp = *i;
   *i = *j;
   *j = temp;
}

void quicksortRec(ListaDupla *array, CelulaDupla *esq, CelulaDupla *dir) {
    CelulaDupla *i = esq, *j = dir;
    CelulaDupla *pivo = i;
    int k=0;
    while(k<(array->size)/2){
        if(pivo->prox != NULL) pivo = pivo->prox;
        k++;
    }
    while (i != j) {
        while (compareHouse(i->personagem,pivo->personagem) < 0){
            if(i->prox != NULL)
            i = i->prox;
        }
        while (compareHouse(j->personagem,pivo->personagem) > 0) {
            if(j->ant != NULL)
            j = j->ant;
        };
        if (compareHouse(i->personagem,j->personagem) <= 0) {
            swap(&i->personagem, &j->personagem);
            if(i->prox != NULL)
            i = i->prox;
            if(j->ant != NULL)
            j = j->ant;
        }
    }
    if (compareHouse(esq->personagem,j->personagem ) < 0)  quicksortRec(array, esq, j);
    if (compareHouse(i->personagem,dir->personagem) < 0)  quicksortRec(array, i, dir);
}

void quicksort(ListaDupla *array) {
    quicksortRec(array, array->primeiro->prox, array->ultimo);
}

int main(){
    int indexMatriz=0, contadorTamanho=0, contadorTamanhoNomes=0;
    char idParaAchar[1001], nomeParaAchar[101];
    char arrayId[405][101];
    Personagens *ptrPersonagens = ptrteste();
    scanf(" %s", idParaAchar);    
    strcpy(arrayId[indexMatriz++],idParaAchar); 
    while(strcmp(idParaAchar,"FIM") != 0){
        scanf(" %s", idParaAchar);
        strcpy(arrayId[indexMatriz++],idParaAchar);        
    }
    for(int i=0; i<sizeof(*arrayId);i++){
        contadorTamanho++;
        if(strcmp(arrayId[i],"FIM") ==0) break;
    }
    Personagens *ponteiroNovo = procuraPorId(ptrPersonagens, arrayId, contadorTamanho);
    ListaDupla listaDupla = new_lista_dupla();
    for(int i=0; i < contadorTamanho-1; i++){
        inserir(&listaDupla, ponteiroNovo[i]);
    }
    quicksort(&listaDupla);
    mostrar(&listaDupla);
    free(ponteiroNovo);
    return 0;
}
