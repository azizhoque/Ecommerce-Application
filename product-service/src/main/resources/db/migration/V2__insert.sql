
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Keyboards', 'Keyboards');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Monitors', 'Monitors');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Display Screens', 'Screens');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Mice', 'Mice');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Accessories', 'Accessories');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Speakers', 'Speakers');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Headsets', 'Headsets');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Webcams', 'Webcams');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Storage', 'Storage');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Chairs', 'Chairs');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Cables', 'Cables');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Power Adapters', 'Adapters');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Printers and Scanners', 'Printers');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Networking Equipment', 'Networking');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Software', 'Software');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Software', 'Software');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Laptop Bags', 'Bags');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Cooling Systems', 'Cooling');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Graphics Cards', 'GraphicsCards');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Cases', 'Cases');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'RAM Modules', 'RAM');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Motherboards', 'Motherboards');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Power Supplies', 'PowerSupplies');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Smart Watches', 'SmartWatches');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Gaming Controllers', 'Controllers');


-- Assuming you already have a sequence named 'product_seq'

-- Insert products for the 'Keyboards' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 10, 'Mechanical keyboard with RGB lighting', 'Mechanical Keyboard 1', 99.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 15, 'Wireless compact keyboard', 'Wireless Compact Keyboard 1', 79.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 20, 'Backlit gaming keyboard with customizable keys', 'Gaming Keyboard 1', 129.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 25, 'Mechanical keyboard with wrist rest', 'Ergonomic Keyboard 1', 109.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 18, 'Wireless keyboard and mouse combo', 'Wireless Combo 1', 69.99, (SELECT id FROM category WHERE name = 'Keyboards'));

-- Insert products for the 'Monitors' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 30, '27-inch IPS monitor with 4K resolution', '4K Monitor 1', 399.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 25, 'Ultra-wide gaming monitor with HDR support', 'Ultra-wide Gaming Monitor 1', 499.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 22, '24-inch LED monitor for office use', 'Office Monitor 1', 179.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 28, '32-inch curved monitor with AMD FreeSync', 'Curved Monitor 1', 329.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 35, 'Portable USB-C monitor for laptops', 'Portable Monitor 1', 249.99, (SELECT id FROM category WHERE name = 'Monitors'));

-- Insert products for the 'Screens' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 15, 'Curved OLED gaming screen with 240Hz refresh rate', 'Curved OLED Gaming Screen 1', 799.99, (SELECT id FROM category WHERE name = 'Screens')),
    (nextval('product_seq'), 18, 'Flat QLED monitor with 1440p resolution', 'QLED Monitor 1', 599.99, (SELECT id FROM category WHERE name = 'Screens')),
    (nextval('product_seq'), 22, '27-inch touch screen display for creative work', 'Touch Screen Display 1', 699.99, (SELECT id FROM category WHERE name = 'Screens')),
    (nextval('product_seq'), 20, 'Ultra-slim 4K HDR display for multimedia', 'Ultra-slim 4K HDR Display 1', 449.99, (SELECT id FROM category WHERE name = 'Screens')),
    (nextval('product_seq'), 25, 'Gaming projector with low input lag', 'Gaming Projector 1', 899.99, (SELECT id FROM category WHERE name = 'Screens'));

-- Insert products for the 'Mice' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 30, 'Wireless gaming mouse with customizable RGB lighting', 'RGB Gaming Mouse 1', 59.99, (SELECT id FROM category WHERE name = 'Mice')),
    (nextval('product_seq'), 28, 'Ergonomic wired mouse for productivity', 'Ergonomic Wired Mouse 1', 29.99, (SELECT id FROM category WHERE name = 'Mice')),
    (nextval('product_seq'), 32, 'Ambidextrous gaming mouse with high DPI', 'Ambidextrous Gaming Mouse 1', 69.99, (SELECT id FROM category WHERE name = 'Mice')),
    (nextval('product_seq'), 26, 'Travel-sized compact mouse for laptops', 'Travel Mouse 1', 19.99, (SELECT id FROM category WHERE name = 'Mice')),
    (nextval('product_seq'), 35, 'Vertical ergonomic mouse for reduced strain', 'Vertical Ergonomic Mouse 1', 39.99, (SELECT id FROM category WHERE name = 'Mice'));

-- Insert products for the 'Accessories' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 25, 'Adjustable laptop stand with cooling fan', 'Adjustable Laptop Stand 1', 34.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 20, 'Wireless charging pad for smartphones', 'Wireless Charging Pad 1', 24.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 28, 'Gaming headset stand with RGB lighting', 'RGB Headset Stand 1', 49.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 22, 'Bluetooth mechanical keypad for tablets', 'Bluetooth Keypad 1', 39.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 30, 'External hard drive enclosure with USB-C', 'External Hard Drive Enclosure 1', 29.99, (SELECT id FROM category WHERE name = 'Accessories'));

-- Products for 'Speakers' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 20, 'Bluetooth speaker with deep bass', 'Bluetooth Speaker 1', 49.99, (SELECT id FROM category WHERE name = 'Speakers')),
    (nextval('product_seq'), 15, '2.1 channel computer speaker system with subwoofer', 'Speaker System 1', 89.99, (SELECT id FROM category WHERE name = 'Speakers')),
    (nextval('product_seq'), 25, 'Compact USB-powered desktop speakers', 'Desktop Speakers 1', 29.99, (SELECT id FROM category WHERE name = 'Speakers'));

-- Products for 'Headsets' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 22, 'Wireless gaming headset with surround sound', 'Wireless Gaming Headset 1', 99.99, (SELECT id FROM category WHERE name = 'Headsets')),
    (nextval('product_seq'), 30, 'Noise-cancelling headset for office calls', 'Noise-Cancelling Headset 1', 69.99, (SELECT id FROM category WHERE name = 'Headsets')),
    (nextval('product_seq'), 18, 'Over-ear headset with detachable microphone', 'Over-Ear Headset 1', 54.99, (SELECT id FROM category WHERE name = 'Headsets'));

-- Products for 'Webcams' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 28, '1080p HD webcam with built-in microphone', 'HD Webcam 1', 39.99, (SELECT id FROM category WHERE name = 'Webcams')),
    (nextval('product_seq'), 16, '4K webcam with auto-focus for streaming', '4K Streaming Webcam 1', 89.99, (SELECT id FROM category WHERE name = 'Webcams')),
    (nextval('product_seq'), 24, 'Webcam with adjustable clip and privacy shutter', 'Clip-On Webcam 1', 24.99, (SELECT id FROM category WHERE name = 'Webcams'));

-- Products for 'Storage' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 35, '1TB portable SSD with USB-C connectivity', 'Portable SSD 1TB 1', 109.99, (SELECT id FROM category WHERE name = 'Storage')),
    (nextval('product_seq'), 20, '2TB external hard drive for backups', 'External HDD 2TB 1', 79.99, (SELECT id FROM category WHERE name = 'Storage')),
    (nextval('product_seq'), 40, '128GB USB 3.0 flash drive', 'USB Flash Drive 128GB 1', 19.99, (SELECT id FROM category WHERE name = 'Storage'));

-- Products for 'Chairs' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 10, 'Ergonomic office chair with lumbar support', 'Ergonomic Office Chair 1', 199.99, (SELECT id FROM category WHERE name = 'Chairs')),
    (nextval('product_seq'), 8, 'Gaming chair with reclining backrest', 'Gaming Chair 1', 249.99, (SELECT id FROM category WHERE name = 'Chairs')),
    (nextval('product_seq'), 12, 'Mesh-back chair with adjustable armrests', 'Mesh Office Chair 1', 149.99, (SELECT id FROM category WHERE name = 'Chairs'));
    
-- Products for 'Cables' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 50, 'Braided USB-C to USB-C cable, 2 meters', 'USB-C Cable 2m 1', 14.99, (SELECT id FROM category WHERE name = 'Cables')),
    (nextval('product_seq'), 40, 'HDMI 2.1 cable supporting 4K 120Hz', 'HDMI Cable 4K 1', 19.99, (SELECT id FROM category WHERE name = 'Cables')),
    (nextval('product_seq'), 35, 'DisplayPort to HDMI adapter cable', 'DisplayPort HDMI Cable 1', 17.99, (SELECT id FROM category WHERE name = 'Cables'));

-- Products for 'Adapters' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 30, '65W USB-C fast charging power adapter', 'USB-C Power Adapter 65W 1', 34.99, (SELECT id FROM category WHERE name = 'Adapters')),
    (nextval('product_seq'), 22, 'Universal laptop charger with multiple tips', 'Universal Laptop Charger 1', 44.99, (SELECT id FROM category WHERE name = 'Adapters')),
    (nextval('product_seq'), 28, 'Multi-port travel adapter with surge protection', 'Travel Power Adapter 1', 24.99, (SELECT id FROM category WHERE name = 'Adapters'));

-- Products for 'Printers' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 12, 'Wireless all-in-one inkjet printer', 'Wireless Inkjet Printer 1', 149.99, (SELECT id FROM category WHERE name = 'Printers')),
    (nextval('product_seq'), 10, 'Compact monochrome laser printer for office', 'Laser Printer 1', 179.99, (SELECT id FROM category WHERE name = 'Printers')),
    (nextval('product_seq'), 8, 'Portable document scanner with auto-feed', 'Document Scanner 1', 129.99, (SELECT id FROM category WHERE name = 'Printers'));

-- Products for 'Networking' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 18, 'Dual-band WiFi 6 router', 'WiFi 6 Router 1', 129.99, (SELECT id FROM category WHERE name = 'Networking')),
    (nextval('product_seq'), 25, '8-port gigabit ethernet switch', 'Gigabit Switch 8-Port 1', 39.99, (SELECT id FROM category WHERE name = 'Networking')),
    (nextval('product_seq'), 20, 'USB WiFi adapter with high-gain antenna', 'USB WiFi Adapter 1', 22.99, (SELECT id FROM category WHERE name = 'Networking'));

-- Products for 'Software' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 100, 'Antivirus software 1-year license, single device', 'Antivirus License 1-Year 1', 29.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 80, 'Office productivity suite, lifetime license', 'Office Suite License 1', 99.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 60, 'PDF editor software annual subscription', 'PDF Editor Subscription 1', 39.99, (SELECT id FROM category WHERE name = 'Software'));
    

-- Products for 'Software' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 100, 'Antivirus software 1-year license, single device', 'Antivirus License 1-Year 1', 29.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 80, 'Office productivity suite, lifetime license', 'Office Suite License 1', 99.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 60, 'PDF editor software annual subscription', 'PDF Editor Subscription 1', 39.99, (SELECT id FROM category WHERE name = 'Software'));

-- Products for 'Bags' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 25, 'Padded laptop backpack with USB charging port', 'Laptop Backpack 1', 49.99, (SELECT id FROM category WHERE name = 'Bags')),
    (nextval('product_seq'), 18, 'Slim laptop sleeve for 15-inch laptops', 'Laptop Sleeve 15-inch 1', 19.99, (SELECT id FROM category WHERE name = 'Bags')),
    (nextval('product_seq'), 20, 'Water-resistant messenger bag for laptops', 'Laptop Messenger Bag 1', 39.99, (SELECT id FROM category WHERE name = 'Bags'));

-- Products for 'Cooling' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 30, 'RGB liquid CPU cooler, 240mm radiator', 'Liquid CPU Cooler 240mm 1', 89.99, (SELECT id FROM category WHERE name = 'Cooling')),
    (nextval('product_seq'), 22, 'Laptop cooling pad with dual fans', 'Laptop Cooling Pad 1', 24.99, (SELECT id FROM category WHERE name = 'Cooling')),
    (nextval('product_seq'), 15, 'High-airflow case fan, 120mm, 3-pack', 'Case Fan 120mm 3-Pack 1', 29.99, (SELECT id FROM category WHERE name = 'Cooling'));

-- Products for 'GraphicsCards' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 8, 'Mid-range graphics card with 8GB VRAM', 'Graphics Card 8GB 1', 349.99, (SELECT id FROM category WHERE name = 'GraphicsCards')),
    (nextval('product_seq'), 5, 'High-end graphics card with 16GB VRAM for 4K gaming', 'Graphics Card 16GB 1', 899.99, (SELECT id FROM category WHERE name = 'GraphicsCards')),
    (nextval('product_seq'), 12, 'Entry-level graphics card for everyday use', 'Graphics Card Entry-Level 1', 179.99, (SELECT id FROM category WHERE name = 'GraphicsCards'));

-- Products for 'Cases' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 14, 'Mid-tower ATX case with tempered glass panel', 'ATX Mid-Tower Case 1', 79.99, (SELECT id FROM category WHERE name = 'Cases')),
    (nextval('product_seq'), 10, 'Compact mini-ITX case for small builds', 'Mini-ITX Case 1', 69.99, (SELECT id FROM category WHERE name = 'Cases')),
    (nextval('product_seq'), 16, 'Full-tower case with extensive cable management', 'Full-Tower Case 1', 119.99, (SELECT id FROM category WHERE name = 'Cases'));

-- Products for 'RAM' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 40, '16GB DDR4 RAM kit, 3200MHz', 'DDR4 RAM 16GB 1', 54.99, (SELECT id FROM category WHERE name = 'RAM')),
    (nextval('product_seq'), 25, '32GB DDR5 RAM kit, 6000MHz', 'DDR5 RAM 32GB 1', 129.99, (SELECT id FROM category WHERE name = 'RAM')),
    (nextval('product_seq'), 30, '8GB DDR4 RAM single stick, 2666MHz', 'DDR4 RAM 8GB 1', 27.99, (SELECT id FROM category WHERE name = 'RAM'));

-- Products for 'Motherboards' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 12, 'ATX motherboard with WiFi 6 and PCIe 5.0', 'ATX Motherboard WiFi 1', 199.99, (SELECT id FROM category WHERE name = 'Motherboards')),
    (nextval('product_seq'), 9, 'Micro-ATX motherboard for budget builds', 'Micro-ATX Motherboard 1', 109.99, (SELECT id FROM category WHERE name = 'Motherboards')),
    (nextval('product_seq'), 7, 'Mini-ITX motherboard for compact builds', 'Mini-ITX Motherboard 1', 149.99, (SELECT id FROM category WHERE name = 'Motherboards'));

-- Products for 'PowerSupplies' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 20, '750W 80+ Gold certified power supply', 'PSU 750W Gold 1', 99.99, (SELECT id FROM category WHERE name = 'PowerSupplies')),
    (nextval('product_seq'), 15, '1000W fully modular power supply', 'PSU 1000W Modular 1', 159.99, (SELECT id FROM category WHERE name = 'PowerSupplies')),
    (nextval('product_seq'), 25, '550W standard power supply for budget builds', 'PSU 550W Standard 1', 49.99, (SELECT id FROM category WHERE name = 'PowerSupplies'));

-- Products for 'SmartWatches' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 22, 'Fitness smartwatch with heart rate monitor', 'Fitness Smartwatch 1', 129.99, (SELECT id FROM category WHERE name = 'SmartWatches')),
    (nextval('product_seq'), 18, 'Premium smartwatch with AMOLED display', 'Premium Smartwatch 1', 249.99, (SELECT id FROM category WHERE name = 'SmartWatches')),
    (nextval('product_seq'), 30, 'Budget smartwatch with basic activity tracking', 'Budget Smartwatch 1', 39.99, (SELECT id FROM category WHERE name = 'SmartWatches'));

-- Products for 'Controllers' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 28, 'Wireless gaming controller with haptic feedback', 'Wireless Controller 1', 59.99, (SELECT id FROM category WHERE name = 'Controllers')),
    (nextval('product_seq'), 20, 'Pro gaming controller with paddle buttons', 'Pro Gaming Controller 1', 79.99, (SELECT id FROM category WHERE name = 'Controllers')),
    (nextval('product_seq'), 32, 'Retro-style USB gaming controller', 'Retro USB Controller 1', 19.99, (SELECT id FROM category WHERE name = 'Controllers'));