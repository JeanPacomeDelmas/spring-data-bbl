explain analyse
select b1_0.id,b1_0.name from barman b1_0
where b1_0.id=1
;

explain analyse
select c1_0.barmans_id,c1_1.id,c1_1.name from barman_cocktails c1_0
                                                  join cocktail c1_1 on c1_1.id=c1_0.cocktails_id
where c1_0.barmans_id=1
;

explain analyse
select b1_0.id,c1_0.barmans_id,c1_1.id,c1_1.name,b1_0.name from barman b1_0
                                                                    left join barman_cocktails c1_0 on b1_0.id=c1_0.barmans_id
                                                                    left join cocktail c1_1 on c1_1.id=c1_0.cocktails_id
where b1_0.id=1
;

