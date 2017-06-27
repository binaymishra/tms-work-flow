insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(1, 'Industrial & Commercial Bank of China', 'BNK001', 'bn@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(2, 'China Construction Bank Corp.', 		'BNK002', 'bm@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(3, 'Agricultural Bank of China', 			'BNK003', 'bn@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(4, 'Bank of China', 						'BNK004', 'bn@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(5, 'HSBC Holdings', 						'BNK005', 'sv@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(6, 'JPMorgan Chase & Co.', 					'BNK006', 'bv@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(7, 'BNP Paribas', 							'BNK007', 'bn@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(8, 'Mitsubishi UFJ Financial Group', 		'BNK008', 'ji@db.com', 'inactive');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(9, 'Bank of America',						'BNK009', 'sv@db.com', 'active');
insert into tt_cpty(id, name, aladdin_code, approved_by, status) values(10, 'Credit Agricole Group', 				'BNK010',  null, 'pending');

insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id) values(1, 300, 	'credit_limit', 		'approved', 	'sv@db.com', 	1);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id) values(2, 100, 	'credit_limit', 		'approved', 	'sv@db.com', 	3);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id) values(3, 200, 	'credit_limit', 		'rejected', 	'bm@db.com', 	3);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id) values(4, 500, 	'fund_treasury_limit',  'pending', 		 null, 			2);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id) values(5, 600, 	'fund_treasury_limit',  'approved', 	'bh@db.com', 	9);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id) values(6, 1000, 	'fund_treasury_limit', 	'rejected', 	'su@db.com', 	7);

