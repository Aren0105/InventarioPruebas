
INSERT INTO categorias (nombre) VALUES ('Aceites y Lubricantes');
INSERT INTO categorias (nombre) VALUES ('Filtros');
INSERT INTO categorias (nombre) VALUES ('Frenos');
INSERT INTO categorias (nombre) VALUES ('Baterías');
INSERT INTO categorias (nombre) VALUES ('Accesorios');
INSERT INTO categorias (nombre) VALUES ('Suspensión');
INSERT INTO categorias (nombre) VALUES ('Iluminación');
INSERT INTO categorias (nombre) VALUES ('Neumáticos');


INSERT INTO productos (nombre, categoria_id, stock, sku, precio, descripcion) VALUES
('Aceite Castrol GTX 5W-30', 1, 50, 'ACE-001', 120.50, 'Aceite de motor de tecnología sintética para una máxima protección contra el desgaste.'),
('Aceite Mobil Super 10W-40', 1, 40, 'ACE-002', 98.90, 'Aceite semisintético premium diseñado para prolongar la vida útil del motor.'),
('Aceite Shell Helix Ultra 5W-40', 1, 35, 'ACE-003', 145.00, 'Lubricante 100% sintético formulado para un rendimiento superior en condiciones extremas.'),
('Aceite Valvoline 20W-50', 1, 25, 'ACE-004', 89.50, 'Aceite mineral de alta viscosidad ideal para vehículos de alto kilometraje.'),
('Lubricante Total Quartz 9000', 1, 30, 'ACE-005', 135.00, 'Aceite sintético avanzado que reduce la fricción y optimiza el consumo de combustible.'),

('Filtro de aceite Toyota', 2, 45, 'FIL-001', 25.50, 'Filtro de aceite original Toyota para mantener el motor libre de impurezas.'),
('Filtro de aire Nissan', 2, 38, 'FIL-002', 35.00, 'Filtro de aire de alto flujo que mejora la aceleración y la eficiencia del motor.'),
('Filtro de combustible Hyundai', 2, 22, 'FIL-003', 42.50, 'Filtro de combustible diseñado para proteger el sistema de inyección y optimizar el consumo.'),
('Filtro de cabina Kia', 2, 28, 'FIL-004', 30.00, 'Filtro de carbón activado que purifica el aire dentro del habitáculo, bloqueando polvo y olores.'),
('Filtro de aceite Bosch', 2, 50, 'FIL-005', 27.90, 'Filtro de aceite de calidad premium con alta capacidad de retención de suciedad.'),

('Pastillas de freno Bosch', 3, 20, 'FRE-001', 155.00, 'Juego de pastillas de freno de alto rendimiento con reducción de ruido y vibración.'),
('Discos de freno Brembo', 3, 15, 'FRE-002', 250.00, 'Discos de freno ventilados para una disipación de calor óptima y frenado seguro.'),
('Zapatas de freno Toyota', 3, 18, 'FRE-003', 130.00, 'Zapatas de freno originales para tambor, garantizando durabilidad y precisión.'),
('Líquido de frenos DOT4', 3, 60, 'FRE-004', 35.50, 'Líquido de frenos sintético con alto punto de ebullición para máxima seguridad.'),
('Kit de frenos delanteros', 3, 10, 'FRE-005', 320.00, 'Kit completo que incluye discos y pastillas cerámicas para el eje delantero.'),

('Batería Bosch 12V 60Ah', 4, 12, 'BAT-001', 350.00, 'Batería de libre mantenimiento con excelente potencia de arranque en frío.'),
('Batería Etna 12V 70Ah', 4, 10, 'BAT-002', 420.00, 'Batería de larga duración ideal para vehículos con alto requerimiento eléctrico.'),
('Batería Record 12V 55Ah', 4, 14, 'BAT-003', 310.00, 'Batería confiable y resistente, diseñada para soportar cambios drásticos de temperatura.'),
('Cargador portátil batería', 4, 8, 'BAT-004', 280.00, 'Arrancador portátil (Jump Starter) compacto con linterna y puertos USB adicionales.'),
('Probador de batería digital', 4, 16, 'BAT-005', 95.00, 'Herramienta de diagnóstico para medir el estado de carga y vida útil de la batería.'),

('Soporte para celular', 5, 80, 'ACC-001', 25.00, 'Soporte magnético universal para montaje en la rejilla de ventilación del auto.'),
('Cargador USB para auto', 5, 65, 'ACC-002', 18.50, 'Cargador de carga rápida con doble puerto USB adaptable al encendedor.'),
('Ambientador vainilla', 5, 100, 'ACC-003', 12.00, 'Ambientador colgante de larga duración con fragancia clásica a vainilla.'),
('Cobertor para volante', 5, 35, 'ACC-004', 40.00, 'Funda de cuero sintético antideslizante para proteger y decorar el volante.'),
('Alfombras universales', 5, 20, 'ACC-005', 75.00, 'Juego de 4 alfombras de goma resistentes, recortables y fáciles de lavar.'),

('Amortiguador delantero KYB', 6, 14, 'SUS-001', 280.00, 'Amortiguador a gas presurizado para restaurar la estabilidad original del vehículo.'),
('Amortiguador trasero Monroe', 6, 16, 'SUS-002', 260.00, 'Amortiguador de reemplazo directo que ofrece confort y control superior en ruta.'),
('Resorte de suspensión Toyota', 6, 12, 'SUS-003', 150.00, 'Resorte helicoidal de acero de alta resistencia para la suspensión delantera.'),
('Brazo de suspensión Nissan', 6, 10, 'SUS-004', 175.00, 'Brazo de control inferior completo con rótula y bujes preinstalados.'),
('Kit de suspensión deportiva', 6, 6, 'SUS-005', 850.00, 'Conjunto de espirales y amortiguadores para reducir la altura y mejorar la aerodinámica.'),

('Faro delantero LED', 7, 18, 'LUC-001', 220.00, 'Faro de reemplazo con tecnología LED para una mayor visibilidad y alcance nocturno.'),
('Foco halógeno H4', 7, 50, 'LUC-002', 20.00, 'Foco halógeno estándar de luz blanca cálida para luces principales.'),
('Luces LED interiores', 7, 45, 'LUC-003', 45.00, 'Kit de tiras LED RGB con control remoto y sincronización de música para el habitáculo.'),
('Neblineros universales', 7, 15, 'LUC-004', 180.00, 'Faros antiniebla de alta penetración ideales para lluvia intensa o neblina.'),
('Barra LED off-road', 7, 8, 'LUC-005', 390.00, 'Barra de luces LED de alta potencia con carcasa de aluminio, ideal para rutas todoterreno.'),

('Neumático Michelin 205/55R16', 8, 24, 'NEU-001', 420.00, 'Llanta de alto desempeño con excelente agarre en suelo mojado y frenado preciso.'),
('Neumático Bridgestone 195/65R15', 8, 20, 'NEU-002', 390.00, 'Llanta duradera y de bajo ruido de rodadura, ideal para el manejo urbano diario.'),
('Neumático Pirelli 17 pulgadas', 8, 18, 'NEU-003', 510.00, 'Neumático deportivo que ofrece máxima estabilidad y control en curvas pronunciadas.'),
('Neumático Goodyear Wrangler', 8, 14, 'NEU-004', 620.00, 'Llanta todoterreno (A/T) robusta con paredes laterales reforzadas para off-road.'),
('Kit reparador de neumáticos', 8, 30, 'NEU-005', 65.00, 'Set de herramientas y tarugos (mechas) para la reparación rápida de pinchazos.'),

('Aceite Repsol Elite 5W-30', 1, 28, 'ACE-006', 125.00, 'Lubricante sintético de última generación que cumple con las exigencias de fabricantes europeos.'),
('Aceite Motul 8100 X-clean', 1, 18, 'ACE-007', 180.00, 'Aceite 100% sintético diseñado específicamente para motores con filtro de partículas (DPF).'),
('Filtro Mann Filter', 2, 26, 'FIL-006', 38.00, 'Filtro de aire de calidad de equipo original para máxima protección contra contaminantes.'),
('Pastillas Brembo Cerámicas', 3, 11, 'FRE-006', 210.00, 'Pastillas premium de compuesto cerámico que reducen drásticamente el polvo en los rines.'),
('Batería portátil para auto', 4, 9, 'BAT-006', 450.00, 'Estación de energía portátil con pinzas inteligentes y protección contra polaridad inversa.'),

('Organizador para maletera', 5, 40, 'ACC-006', 55.00, 'Caja plegable con múltiples compartimentos para mantener el baúl ordenado y los objetos seguros.'),
('Cámara de retroceso HD', 5, 22, 'ACC-007', 160.00, 'Cámara trasera a prueba de agua con visión nocturna y líneas guías de estacionamiento.'),
('Sensor de estacionamiento', 5, 19, 'ACC-008', 145.00, 'Sistema de 4 sensores ultrasónicos con pequeña pantalla LED indicadora y alerta sonora.'),

('Amortiguador deportivo Bilstein', 6, 7, 'SUS-006', 480.00, 'Amortiguador de alto desempeño diseñado para entusiastas de la conducción deportiva.'),
('Kit elevación suspensión', 6, 5, 'SUS-007', 920.00, 'Kit de levante (lift kit) de 2 pulgadas para mejorar el despeje del suelo en camionetas y 4x4.'),

('Foco LED H7', 7, 44, 'LUC-006', 35.00, 'Bombilla LED ultrabrillante tipo H7 con disipador térmico de aluminio integrado.'),
('Luces DRL universales', 7, 17, 'LUC-007', 130.00, 'Luces de conducción diurna LED de bajo consumo, mejorando la visibilidad del vehículo de día.'),

('Neumático Continental Sport', 8, 12, 'NEU-006', 560.00, 'Neumático premium con tecnología de frenado superior a altas velocidades y pavimento mojado.'),
('Compresor portátil de aire', 8, 21, 'NEU-007', 140.00, 'Inflador de llantas digital de 12V con apagado automático al alcanzar la presión programada.');