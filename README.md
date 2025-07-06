# ✈️ Sistema de Reserva de Vuelos - Java Swing

Este proyecto es un **sistema completo de reserva de vuelos** desarrollado en Java, 
con interfaces gráficas en Swing. Integra múltiples patrones de diseño y aplica los
principios SOLID para garantizar una **arquitectura robusta** asi como una excelente **escalabilidad** y **mantenibilidad** del codigo.

---

## 🧩 Tecnologías y herramientas utilizadas

- **Java SE 11+**
- **Swing (GUI)**
- **LGoodDatePicker** (para la selección de fechas)
- **iText PDF** (para generación de comprobantes)
- **Maven / IDE IntelliJ / NetBeans**
- Diseño modular y OOP

---

## 🧠 Patrones de diseño implementados

| Patrón        | Uso                                                                 |
|--------------|----------------------------------------------------------------------|
| **Builder**  | Construcción flexible de objetos `ReservaVuelo`                      |
| **Factory**  | Instanciación dinámica de tipos de vuelos (Económico / Ejecutivo)    |
| **Adapter**  | Comunicación con un proveedor de vuelos externo simulado             |
| **Decorator**| Agregado de servicios extras como equipaje, acompañante, silla, etc. |
| **Strategy** | Procesamiento de pagos con distintos métodos (Tarjeta, PayPal)       |
| **Singleton**| Gestión única del usuario                                            |

---

## 🖥️ Módulos funcionales

### 1. Selección de tipo de viaje
- Viaje **sencillo** o **redondo**
- Fecha de salida y regreso
- Clase: Económica / Ejecutiva
- Origen y destino

### 2. Selección de vuelo
- Vuelos simulados según fecha
- Precios calculados dinámicamente con base en clase y fecha

### 3. Selección de asiento y extras
- Selección visual de asiento(s)
- Servicios: maleta adicional, asistencia especial, acompañante adulto mayor

### 4. Registro de datos del pasajero
- Validación estricta (nombres, fecha, teléfono, nacionalidad)
- Fecha de nacimiento con calendario
- Asistencia en aeropuerto opcional

### 5. Pago
- Elección entre **Tarjeta** y **PayPal**
- Validación de campos según tipo de pago
- Estilización de campos
- Pago simulado usando patrón Strategy

### 6. Confirmación y PDF
- Resumen visual de toda la reserva
- Generación de comprobante PDF con iText
- Registro simulado con proveedor externo (adapter)

---
```
## 🗃️ Estructura de paquetes
org.main.java.com.vuelosreservas
│
├── adapterVueloExterno # Comunicación externa simulada
    ├────ProveedorExterno
    ├────Servicio
    ├────VueloAdapter
├── builderVuelo # Builder para ReservaVuelo
    ├────ReservaBuilder
    ├────ReservaVuelo
├── decorator # Decoradores para extras
    ├────ExtraAcompaniante
    ├────ExtraAsiento
    ├────ExtraEquipaje
    ├────ExtraSillaRueda
    ├────ReservaBasica
    ├────ReservaExtra
├── factory # Fábricas de tipo de vuelo
    ├────ServicioVuelo
    ├────VueloEconomico
    ├────VueloEconomicoFactory
    ├────VueloEjecutivo
    ├────VueloEjecutivoFactory
    ├────VueloFactory
├── reserva # Modelo de reserva final
    ├────ReservaFinal
├── singletonUsuario # Gestión de usuario singleton
    ├────Usuario
├── strategy # Pagos con Strategy Pattern
    ├────MetodoPago
    ├────PagoPaypal
    ├────PagoTarjeta
    ├────ProcesadorPago
├── ui # Interfaces gráficas (Swing)
    ├────DatosPasajeroVentana
    ├────PagoVentana
    ├────ResumenFinalVentana
    ├────SeleccionExtrasVentana
    ├────SeleccionTipoViaje
    ├────SeleccionVueloVentana

```

---

## ⚙️ Requisitos

- Java 11+
- Biblioteca [LGoodDatePicker](https://github.com/LGoodDatePicker/LGoodDatePicker)
- Biblioteca [iText 2.1.7](https://mvnrepository.com/artifact/com.lowagie/itext/2.1.7) (para PDF)

Puedes añadirlas vía Maven o como dependencias externas si trabajas desde IDE.

---

## 🚀 Ejecución

```java
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(SeleccionTipoViajeVentana::new);
    }
}
```

## 📌 Notas adicionales
Los precios base se generan dinámicamente con base en la clase y el día del vuelo.

El asiento premium (fila A1 - F1) tiene recargo.

Los servicios adicionales son completamente opcionales.

## Autor
Desarrollado con ❤️ por HAYDEE BONILLA, ALEJANDRO SOLIS Y DAVID VENTURA

## 🔧 Colaboraciones, sugerencias y mejoras son bienvenidas.
