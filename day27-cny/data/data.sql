
set @po_id = 'abcd1234';

insert into purchase_orders(po_id, name, address, delivery_date)
    values (@po_id, 'fred', '1 bedrock', '2025-01-31');

insert into line_items(name, quantity, unit_price, po_id) values
    ('apple', 10, .5, @po_id),
    ('grapes', 1, 5, @po_id),
    ('pear', 10, 5, @po_id);
