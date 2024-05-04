explain analyse
    select b1_0.id,b1_0.name from barman b1_0
        where b1_0.id=1
;

explain analyse
    select c1_0.barman_id,c1_0.id,c1_0.name from cocktail c1_0
        where c1_0.barman_id=1
;

explain analyse
    select b1_0.id,c1_0.barman_id,c1_0.id,c1_0.name,b1_0.name from barman b1_0
        left join cocktail c1_0 on b1_0.id=c1_0.barman_id
        where b1_0.id=2
;

