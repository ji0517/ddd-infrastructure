insert into t_goods(f_goodsid,f_goodsname) values ('1','毛衣');
insert into t_goods(f_goodsid,f_goodsname) values ('2','皮衣');

insert into t_stock(f_goodsid,f_createtime,f_goodsname,f_stocknum,f_updatetime,f_version) values('1', CURRENT_TIMESTAMP(),'毛衣',200,CURRENT_TIMESTAMP(),1);
insert into t_stock(f_goodsid,f_createtime,f_goodsname,f_stocknum,f_updatetime,f_version) values('2', CURRENT_TIMESTAMP(),'皮衣',500,CURRENT_TIMESTAMP(),1);