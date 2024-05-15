explain analyse
    select b1_0.id,b1_0.name from barman b1_0
        where 1 in
            (select c1_0.id from cocktail c1_0 where b1_0.id=c1_0.barman_id)
;

explain analyse
    select b1_0.id,b1_0.name from barman b1_0
        left join cocktail c1_0 on b1_0.id=c1_0.barman_id
        where c1_0.id=1
;

explain analyse
    select b1_0.id,b1_0.name from barman b1_0 join cocktail c1_0 on b1_0.id=c1_0.barman_id
        where 'WHITE_RHUM' in
            (select i1_0.ingredients from cocktail_ingredients i1_0 where c1_0.id=i1_0.cocktail_id)
;
