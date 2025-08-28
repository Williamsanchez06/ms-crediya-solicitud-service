INSERT INTO loan_application.loan_types (
    name, min_amount, max_amount, interest_rate, auto_validation
) VALUES
      ( 'Préstamo Personal',         1000000, 50000000, 18.50, TRUE),
      ( 'Financiamiento de Vehículo',5000000, 100000000, 14.00, FALSE),
      ( 'Crédito Hipotecario',       20000000, 500000000, 11.75, FALSE),
      ( 'Préstamo Educativo',        3000000, 80000000, 12.25, TRUE),
      ( 'Crédito para Negocios',     10000000, 300000000, 16.00, FALSE);

INSERT INTO loan_application.states (
    name, description
) VALUES
      ( 'PENDIENTE', 'Solicitud recibida, en espera de validación.'),
      ('EN VALIDACIÓN', 'La solicitud está siendo evaluada automáticamente o por un analista.'),
      ( 'APROBADO', 'La solicitud ha sido aprobada.'),
      ('RECHAZADO', 'La solicitud ha sido rechazada por no cumplir con los criterios.'),
      ('CANCELADO', 'La solicitud fue cancelada por el usuario o por el sistema.');
