import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
class RegistroScreen extends StatefulWidget {
  @override
  _RegistroScreenState createState() => _RegistroScreenState();
}

class _RegistroScreenState extends State<RegistroScreen> {
  final TextEditingController _nombre = TextEditingController();
  final TextEditingController _apellidos = TextEditingController();
  final TextEditingController _email = TextEditingController();
  final TextEditingController _pass = TextEditingController();
  final TextEditingController _tel = TextEditingController();

  Future<void> registrar() async {
    // Usamos 10.0.2.2 para que el emulador de Android vea tu servidor local
    final url = Uri.parse('http://10.0.2.2:8080/api/auth/signup');
    
    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        "nombre": _nombre.text,
        "apellidos": _apellidos.text,
        "email": _email.text,
        "password": _pass.text,
        "telefono": _tel.text,
        "rol": "comprador"
      }),
    );

    if (response.statusCode == 200) {
      print("¡Registrado en MySQL!");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Registro Speed Spares"), backgroundColor: Colors.orange),
      body: Padding(
        padding: EdgeInsets.all(20),
        child: SingleChildScrollView(
          child: Column(
            children: [
              TextField(controller: _nombre, decoration: InputDecoration(labelText: "Nombre")),
              TextField(controller: _apellidos, decoration: InputDecoration(labelText: "Apellidos")),
              TextField(controller: _email, decoration: InputDecoration(labelText: "Email")),
              TextField(controller: _pass, decoration: InputDecoration(labelText: "Contraseña"), obscureText: true),
              TextField(controller: _tel, decoration: InputDecoration(labelText: "Teléfono")),
              SizedBox(height: 30),
              ElevatedButton(onPressed: registrar, child: Text("Crear Cuenta"))
            ],
          ),
        ),
      ),
    );
  }
}