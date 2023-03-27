module Tema (Tema, nuevoT, nombreT, datosT, etiquetasT, agregarT, aplicaT)
    where

import Tipos

data Tema = Tem Nombre [ Etiqueta ] Datos deriving (Eq, Show, Ord)

nuevoT :: Nombre -> Datos -> Tema
nuevoT nombre = Tem nombre []

nombreT :: Tema -> Nombre
nombreT (Tem nombre _ _) = nombre

datosT :: Tema -> Datos
datosT (Tem _ _ datos) = datos

etiquetasT :: Tema -> [ Etiqueta ]
etiquetasT (Tem _ etiquetas _) = etiquetas

agregarT :: Etiqueta -> Tema -> Tema
agregarT eti (Tem nombre etiquetas datos) = Tem nombre (etiquetas++[eti]) datos

aplicaT :: Etiqueta -> Tema -> Bool
aplicaT eti (Tem nombre etiquetas datos) = eti `elem` etiquetas

{- TEST. 
1. nuevoT
2. nombreT
3. datosT
4. etiquetasT con lista vacía
5. etiquetasT con lista NO vacía
6. aplicaT con lista vacía
7. aplicaT con etiqueta correspondiente
8. aplicaT con etiqueta que no corresponde
-}

tema1 :: Tema
tema1 = nuevoT "Breathe" "Pink Floyd"

tema2 :: Tema
tema2 = agregarT "rock" tema1

temaFinal :: Tema
temaFinal = agregarT "internacional" tema2

temaTest :: [Bool]
temaTest = [ tema1 == Tem "Breathe" [] "Pink Floyd",
    nombreT temaFinal == "Breathe",
    datosT temaFinal == "Pink Floyd",
    null (etiquetasT tema1),
    etiquetasT temaFinal == ["rock","internacional"], -- este "True" nos testea la función agregaT que se uso dos veces para temaFinal
    not (aplicaT "rock" tema1),
    aplicaT "rock" temaFinal,
    not (aplicaT "internacional" tema2)
    ]

