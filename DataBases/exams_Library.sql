select ekdotis.IDekdoti, periodiko.tupos from ekdotis,periodiko, Daneismoi
where (ekdotis.IDekdoti=Daneismoi.IDekdoti and periodiko.IDeidous=Daneismoi.IDeidous and Daneismoi.imerominiaDaneismou='01-01-2014');



select ekdotis.epwnumia from ekdotis, Daneismoi where(ekdotis.IDekdoti=Daneismoi.IDekdoti) 
group by ekdotis.epwnumia having count(*)>=all(select count(*) from Daneismoi,ekdotis where(ekdotis.IDekdoti=Daneismoi.IDekdoti) group by ekdotis.IDekdoti ) order by ekdotis.epwnumia desc;



select eidos.titlos, praktikaSunedriwn.imerominia from eidos, praktikaSunedriwn,proteraiotita
where (eidos.IDeidous=praktikaSunedriwn.IDeidous and proteraiotita.IDeidous=praktikaSunedriwn.IDeidous) 
group by eidos.titlos,praktikaSunedriwn.imerominia having count(*)>=all(select count(*) from proteraiotita,praktikaSunedriwn where (praktikaSunedriwn.IDeidous=proteraiotita.IDeidous) group by praktikaSunedriwn.IDeidous);



(select daneizomenos.IDdaneizomenou, daneizomenos.onoma, daneizomenos.epitheto from daneizomenos
where daneizomenos.IDdaneizomenou in (select daneizomenos.IDdaneizomenou from daneizomenos,Daneismoi,periodiko where daneizomenos.IDdaneizomenou=Daneismoi.IDdaneizomenou and periodiko.IDeidous=Daneismoi.IDeidous))
intersect
(select daneizomenos.IDdaneizomenou, daneizomenos.onoma, daneizomenos.epitheto from daneizomenos
where daneizomenos.IDdaneizomenou in (select daneizomenos.IDdaneizomenou from daneizomenos,Daneismoi,vivlio where daneizomenos.IDdaneizomenou=Daneismoi.IDdaneizomenou and vivlio.IDeidous=Daneismoi.IDeidous));