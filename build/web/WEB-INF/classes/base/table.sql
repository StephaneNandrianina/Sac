/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  hp elitebook 840 G4
 * Created: 22 janv. 2024
 */

create database sac ;
\c sac ;

create table matiere (
     idMatiere serial primary key,
    nomMatiere varchar(100)
);

create table look (
     idLook serial primary key,
    nomLook varchar(100)
);

create table type (
     idType serial primary key,
    nomType varchar(100)
);

create table taille (
     idTaille serial primary key,
    nomTaille varchar(100)
);

create table lookParMatiere (
    idLookParMatiere serial primary key,
    idLook int,
    idMatiere int ,
    foreign key (idLook) references look (idLook) ,
    foreign key (idMatiere) references matiere (idMatiere)
);

create table produit (
    idProduit serial primary key ,
    nomProduit varchar(100),
    idType int ,
    idlook int ,
    foreign key (idType) references type (idType),
    foreign key (idlook) references look(idlook)
);

create table matierePrix(
    idMatierePrix serial primary key,
    idMatiere int ,
    prix double precision ,
    foreign key (idMatiere) references matiere (idMatiere)
);

create table stockMatiereEntree (
     idStockMatiereEntree serial primary key,
     date date,
     idMatiere int ,
     quantiteEntree int ,
     quantiteSortie int ,
     prix double precision,
     foreign key (idMatiere) references matiere ( idMatiere)     
);

-- create table stockMatiereEntree (
--      idStockMatiereEntree serial primary key,
--      date date,
--      idMatiere int ,
--      quantite int ,
--      prix double precision,
--      foreign key (idMatiere) references matiere ( idMatiere)     
-- );

create table nouvelleFormule(
    idNouvelleFormule serial primary key,
    idProduit int,
    idTaille int ,
    idMatiere int,
    quantiteMatiere int,
    foreign key(idProduit) references produit (idProduit),
    foreign key(idTaille) references taille (idTaille),
    foreign key (idMatiere) references matiere (idMatiere)
);

create table fabricationSac (
    idFabricationSac serial primary key ,
    idProduit int, 
    quantite int,
    date date ,
    foreign key(idProduit) references produit (idProduit)
);

create table genre (
     idGenre serial primary key,
     nomGenre varchar(50)
);

create table client (
     idClient serial primary key,
     nomClient varchar(100),
     prenomClient varchar(100),
     date date,
     idGenre int,
     foreign key(idGenre) references genre (idGenre)
);

create table vente(
    idVente serial primary key,
    idProduit int,
    idClient int,
    date date,
    foreign key(idProduit) references produit (idProduit),
    foreign key(idClient) references client (idClient)
);

create table employe (
    idEmploye serial primary key,
    nomEmploye varchar(100),
    prenomEmploye varchar(100),
    dateDeNaissance date
);

create table profil (
    idProfil serial primary key,
    nomProfil varchar(50),
    niveau int 
);

create table embauche(
    idEmboche serial primary key,
    idEmploye int,
    idProfil int,
    tauxHoraire double precision,
    foreign key (idEmploye) references employe (idEmploye),
    foreign key (idProfil) references profil (idProfil)
);

create or replace view V_LookEtLookParMatiere as
select lookParMatiere.idlookparmatiere ,look.idLook, look.nomLook , matiere.nomMatiere
from lookparmatiere
join look on lookparmatiere.idLook = look.idLook
join matiere on lookparmatiere.idMatiere = matiere.idMatiere;

-- VIEW MANAO AJAX MATIERE PAR PRODUIT
CREATE VIEW matieres_par_produit AS
SELECT m.idMatiere, m.nomMatiere, p.idProduit
FROM matiere m
JOIN lookParMatiere lpm ON m.idMatiere = lpm.idMatiere
JOIN produit p ON lpm.idLook = p.idlook;


-- VIEW LISTE DES SACS UTILISANT UNE MATIERE CHOISIE
CREATE OR REPLACE VIEW V_ListeSacParMatiere as
select produit.nomProduit , taille.nomTaille, matiere.nomMatiere, nouvelleformule.quantitematiere,matiere.idmatiere 
from nouvelleformule
join produit on nouvelleformule.idProduit = produit.idProduit
join taille on nouvelleformule.idTaille = taille.idTaille
join matiere on nouvelleformule.idMatiere = matiere.idMatiere
;

-- View total prix de matiere dans un produit 
create or replace view V_totalPrixProduit as 
SELECT mp.idproduit, p.nomproduit as nom ,ty.nomType ,loo.nomLook as nomlook ,SUM(sme.prix) AS somme_prix
FROM matieres_par_produit mp
JOIN stockmatiereentree sme ON mp.idmatiere = sme.idmatiere
join produit p on mp.idProduit = p.idProduit
join type ty on p.idType = ty.idType
join look loo on p.idLook = loo.idLook
-- WHERE mp.idproduit = 1
GROUP BY mp.idproduit , p.nomproduit, ty.nomType,loo.nomLook;

create or replace view V_MatiereParLook as 
select look.idLook , matiere.nomMatiere as nomMatiere
from lookparmatiere
join look on lookparmatiere.idLook=look.idLook
join matiere on lookParMatiere.idMatiere=matiere.idMatiere;

create or replace view viewPostSalairePersonne as
select employe.nomEmploye, profil.nomProfil , embauche.tauxHoraire
from embauche
join employe on embauche.idEmploye = employe.idEmploye
join profil on embauche.idProfil = profil.idProfil ;


create or replace view view_Produit_genre as
select produit.idProduit , produit.nomProduit , client.idClient  from vente 
join produit on vente.idProduit = produit.idProduit
join client on vente.idClient = client.idClient;

create or replace view V_ListeFormulle as
select nouvelleformule.idproduit, produit.nomProduit , taille.nomTaille
from nouvelleformule
join produit on nouvelleformule.idProduit = produit.idProduit
join taille on nouvelleformule.idTaille = taille.idTaille
join matiere on nouvelleformule.idMatiere = matiere.idMatiere
group by nouvelleformule.idproduit, produit.nomProduit , taille.nomTaille
;

create or replace view V_ClientGenreVente as 
select  c.nomclient, c.prenomclient, g.idgenre,g.nomgenre,v.idproduit,v.idvente,v.date
from vente v
join client c on v.idclient= c.idclient
join genre g on c.idgenre = g.idgenre;

-- view somme quantite matiere entree
create or replace view V_SommeQuantiteMatiereEntree as 
select  stm.idmatiere , sum(stm.quantiteentree) as totalQuantite
from stockmatiereentree stm
join matiere on stm.idmatiere = matiere.idmatiere
group by stm.idmatiere, stm.idstockmatiereentree;

select  nf.idproduit , nf.idtaille ,nf.idmatiere, nf.quantitematiere , e.totalquantite  from nouvelleformule nf
join V_SommeQuantiteMatiereEntree e on e.idmatiere = nf.idmatiere
where idproduit = 1 ;

 insert into nouvelleformule values(default,1,1,1,20);
 insert into nouvelleformule values(default,1,2,1,40);
  insert into nouvelleformule values(default,1,1,3,2);
 insert into nouvelleformule values(default,1,2,3,4);