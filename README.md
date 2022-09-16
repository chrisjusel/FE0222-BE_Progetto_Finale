# Energy Service
Questo progetto didattico è finalizzato alla gestione dei dati di un CRM che gestisce clienti e fatture.

# Contenuti presenti
- [Quadro generale](#Quadro-generale)
- [Tecnologie utilizzate](#Tecnologie-utilizzate)
- [Funzionalità](#Funzionalità)
- [Utilizzo API - Esempi](#Utilizzo-API-Esempi)
- [Risorse Aggiuntive](#Risorse-Aggiuntive)


## Quadro generale
Questo applicativo è stata realizzato con Spring ed esponde metodi REST che permettono di effettuare le principali operazioni di lettura e scrittura dei dati.
- E' presente lo strato di persistenza su Database e viene sfruttato il framework JPA, in particolare attraverso l'ORM Hybernate,
per astrarre la logica di business scritta in Java dalle operazioni da svolgere su qual si voglia DBMS sottostante.
- Tutti i metodi che l'API espone sono documentati con Open-Api e Swagger.
- E' inoltre presente un sistema di autenticazione attraverso token JWT.
- All'interno del progetto è stato adottato un sistema di gestione delle eccezioni ed il codice è stato testato con il framework di testing JUnit.
- Ad ogni avvio dell'applicazione, di default, vengono ricaricati tutti i dati di test che vanno a popolare il database attraverso la lettura da file csv. Questo comportamento può essere cambiato nell'application.properties.
- Per le operazioni di lettura è stata implementata la paginazione.


## Tecnologie utilizzate
- Spring Boot
- Spring Data
- Spring Security
- JWT
- Swagger e Open-Api
- JUnit (test)

## Funzionalità
### Province
- Inserimento di una provincia
- Modifica di una provincia
- Recupero di una provincia attraverso l'id
- Recupero di tutte le province

### Comuni
- Inserimento di un comune
- Modifica di un comune
- Rimozione di un comune (se non associato a nessun indirizzo)
- Recupoero di un comune attraverso l'id
- Recupero di tutti i comuni

### Clienti
- Inserimento di un cliente
- Modifica di un cliente (a cascata anche del suo indirizzo)
- Rimozione di un cliente (a cascata di tutte le sue fatture)

##### Operazioni di recupero e ordinamento
- Recupero di un cliente attraverso l'id
- Recupero di tutti i clienti
- Recupero dei clienti tra un range di fatturato annuo
- Recupoero dei clienti tra un range di date di inserimento
- Recupero dei clienti tra un range di date di ultimo contatto
- Recupero dei clienti attraverso la loro ragione sociale o una sua sottostringa
- Ordinamento dei clienti in base al loro fatturato annuo
- Ordinamento dei clienti in ordine alfabetico in base alla ragione sociale
- Ordinamento dei clienti in base alla data di inserimento
- Ordinamento dei clienti in base alla data di ultimo contatto
- Ordinamento dei clienti in base all'ordine alfabetico della provincia della sede legale

### Indirizzi
- Modifica di un indirizzo
- Rimozione di un indirizzo (se non associato ad alcun cliente)
- Recupero di un indirizzo attraverso l'id
- Recupero di tutti gli indirizzi

### Stati fattura
- Inserimento di uno stato fattura
- Modifica di uno stato fattura
- Rimozione di uno stato fattura (se non collegato a nessuna fattura)
- Recupero di uno stato fattura attraverso l'id
- Recupero di tutti gli stati fattura

### Fatture
- Inserimento di una nuova fattura
- Modifica di una nuova fattura
- Rimozione di una fattura

##### Operazioni di recupero
- Recupero di una fattura attraverso l'id
- Recupero di tutte le fatture
- Recupero delle fatture di un cliente
- Recupero delle fatture attraverso lo stato
- Recupero delle fatture attraveros l'anno
- Recupero delle fatture tra un range di importo
- Recupero delle fatture attraverso una data

### Autenticazione
- Registrazione di un nuovo utente (con ruoli)
- Login

## Utilizzo API - Esempi

### Metodi POST
#### Comune

- **Inserimento di un nuovo comune**

```
	{
    	"name": "Fiumicino",
    	"province": {
        	"name": "Roma"
    	}
	}
```

- **Response**

```
{
    "id": {calculated},
    "name": "Fiumicino",
    "province": {
        "id": {calculated},
        "name": "Roma",
        "sign": "RM"
    }
}
```

#### Fattura
- **Inserimento di una nuova fattura**

```
{
    "date":"2019-07-31 16:29:00",
    "number": 489576,
    "year": 2019,
    "amount": 900.16,
    "state": "NON PAGATA",
    "customer": 1
}
```

- **Response**

```
{
    "id": {calculated},
    "year": 2019,
    "date": "2019-07-31 16:29:00",
    "amount": 900.16,
    "number": 489576,
    "customer": {
        "id": 1
    },
    "state": {
        "id": {calculated},
        "name": "NON PAGATA"
    }
}
```

### Metodi PUT
#### Stato fattura
- **Modifica di uno stato fattura**
  - /api/billingstates/{id-fattura}
  
```
{
    "name": "In Attesa"
}
```
- **Response**

```
{
    "id": {id-fattura},
    "name": "In Attesa"
}
```

### Metodi GET
#### Cliente

- **Recupoero di un cliente attraverso l'id**
  - /api/customers/{id-cliente}

```
{
    "id": 6,
    "companyName": "Flashspan",
    "vatNumber": "90-018-5605",
    "email": "bjanko5@yellowbook.com",
    "insertionDate": "2021-10-06 09:15:18",
    "lastContactDate": "2021-10-27 12:40:54",
    "annualTurnover": 205620.78,
    "pec": "bjanko5@a8.net",
    "phone": "+963 685 697 6956",
    "contactEmail": "bjanko5@sourceforge.net",
    "contactName": "Bee",
    "contactSurname": "Janko",
    "contactPhone": "+7 795 964 8280",
    "operatingSiteAddress": {
        "id": 20,
        "street": "Myrtle",
        "civicNumber": "31",
        "locality": "87331",
        "zip": "Syria",
        "common": {
            "id": 1918,
            "name": "Bellano",
            "province": {
                "id": 40,
                "name": "Lecco",
                "sign": "LC"
            }
        }
    },
    "legalSiteAddress": {
        "id": 19,
        "street": "Talisman",
        "civicNumber": "019",
        "locality": "Russia",
        "zip": "88393",
        "common": {
            "id": 4074,
            "name": "Mirabello Sannitico",
            "province": {
                "id": 21,
                "name": "Campobasso",
                "sign": "CB"
            }
        }
    },
    "customerType": "SPA"
}
```
#### Fatture
- **Recupoero delle fatture di un cliente**
  - /api/billings/customer/{id-cliente}
  
- **Response**  

```
{
    "content": [
        {
            "id": 178,
            "year": 2021,
            "date": "2022-04-02 02:03:54",
            "amount": 17132.07,
            "number": 2502,
            "customer": {
                "id": 8
            },
            "state": {
                "id": 5,
                "name": "PAGATA"
            }
        },
        {
            "id": 194,
            "year": 2021,
            "date": "2022-09-11 06:31:32",
            "amount": 43840.67,
            "number": 1965,
            "customer": {
                "id": 8
            },
            "state": {
                "id": 7,
                "name": "IN ATTESA"
            }
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```

### Metodi DELETE
#### Cliente
- **Rimozione di un cliente**
  - /api/customers/1
- **Response**
  - Http Status: 200
  
## Risorse Aggiuntive
- E' fornito il file di esportazione di Postman contentente già la configurazione corretta dei metodi per poterli testare
- URL Documentazione Swagger: {domain-name}/swagger-ui.html
  - Attenzione, per utilizzare i metodi swagger è necessario autenticarsi con il token, ottenibile attraverso il metodo di login con la seguente richiesta: 
  ```
  {
  	"username": "admin",
  	"password": "admin"
  }
  ```
