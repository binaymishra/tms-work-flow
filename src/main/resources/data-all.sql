insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(1, 'Industrial & Commercial Bank of China', 'BNK001', 'bn@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(2, 'China Construction Bank Corp.', 		'BNK002', 'bm@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(3, 'Agricultural Bank of China', 			'BNK003', 'bn@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(4, 'Bank of China', 						'BNK004', 'bn@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(5, 'HSBC Holdings', 						'BNK005', 'sv@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(6, 'JPMorgan Chase & Co.', 					'BNK006', 'bv@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(7, 'BNP Paribas', 							'BNK007', 'bn@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(8, 'Mitsubishi UFJ Financial Group', 		'BNK008', 'ji@db.com', 'inactive', false);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(9, 'Bank of America',						'BNK009', 'sv@db.com', 'active', true);
insert into tt_cpty(id, name, aladdin_code, approved_by, status, enable) values(10, 'Credit Agricole Group', 				'BNK010',  null, 'pending', false);

insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id, enable) values(1, 300, 	'credit_limit', 		'approved', 	'sv@db.com', 	1, true);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id, enable) values(2, 100, 	'credit_limit', 		'approved', 	'sv@db.com', 	3, true);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id, enable) values(3, 200, 	'credit_limit', 		'rejected', 	'bm@db.com', 	3, true);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id, enable) values(4, 500, 	'fund_treasury_limit',  'pending', 		 null, 			2, false);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id, enable) values(5, 600, 	'fund_treasury_limit',  'approved', 	'bh@db.com', 	9, true);
insert into tt_cpty_limits(id, limits, cp_limit_type, status, approved_by, cp_id, enable) values(6, 1000, 	'fund_treasury_limit', 	'rejected', 	'su@db.com', 	7, true);

insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(1, 2000, 500, 25000, 'USD', 2);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(2, 3000, 200, 25000, 'USD', 3);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(3, 6000, 300, 25000, 'EUR', 3);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(4, 3000, 500, 25000, 'USD', 10);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(5, 1000, 600, 25000, 'EUR', 9);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(6, 3000, 500, 25000, 'USD', 8);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(7, 3000, 400, 25000, 'EUR', 7);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(8, 7000, 500, 25000, 'EUR', 7);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(9, 3000, 900, 25000, 'USD', 5);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(10, 8000, 700, 25000, 'EUR', 4);
insert into tt_cpty_market_data(id, capacity, ticket_size, rounding_factor, currency, cp_id) values(11, 4000, 500, 25000, 'USD', 1);

insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(1, 'A', 'F001', 'S000', 'M001', 6);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(2, 'B', 'F000', 'S001', 'M007', 1);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(3, 'A', 'F001', 'S003', 'M001', 3);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(4, 'B', 'F004', 'S001', 'M001', 4);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(5, 'A', 'F001', 'S002', 'M009', 2);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(6, 'C', 'F002', 'S006', 'M001', 1);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(7, 'A', 'F005', 'S007', 'M009', 7);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(8, 'D', 'F001', 'S001', 'M001', 8);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(9, 'A', 'F004', 'S009', 'M008', 6);
insert into tt_cpty_rating(id, rating_type, fitch_rating, sadnp_rating, moody_rating, cp_id) values(10, 'D', 'F006', 'S000', 'M001', 7);

