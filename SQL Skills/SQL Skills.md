##MySQL
```sql
    SELECT GROUP_CONCAT(DISTINCT CONCAT("'",order_no,"'"))
      FROM yyplandw.fct_sales
     WHERE dim_date_id BETWEEN 20200301 AND 20200330
```
ps:该方法可以将一个字段的值拼接成一个用逗号隔开的字符串