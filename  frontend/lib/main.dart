import 'package:flutter/material.dart';
import 'registro_screen.dart'; // Asegúrate de que el nombre coincida con el archivo

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Speed Spares',
      theme: ThemeData(
        primarySwatch: Colors.orange,
      ),
      home: RegistroScreen(), // Aquí ya no debería salir rojo si guardaste el otro archivo
    );
  }
}
