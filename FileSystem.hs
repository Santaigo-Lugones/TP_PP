module FileSystem (FileSystem, nuevoF, etiquetasF, temasF, agregarF, filtrarF)
    where

import Tipos
import Tema

data FileSystem = FS [Etiqueta] [Tema] deriving (Eq, Show)

nuevoF :: FileSystem
nuevoF = FS [] []

etiquetasF :: FileSystem -> [ Etiqueta ]
etiquetasF (FS etiquetas temas) = etiquetas

temasF :: FileSystem -> [ Tema ]
temasF (FS etiquetas temas) = temas

agregarF :: Tema -> FileSystem -> FileSystem
agregarF nuevoTema (FS etiquetas temas) = FS etiquetas (insertar nuevoTema temas)

filtrarF :: Etiqueta -> FileSystem -> [ Tema ]
filtrarF eti (FS etiquetas temas) = [x | x <- temas, aplicaT eti x]

{- TEST
1. nuevoF
2. etiquetasF
3. temasF
4. agregarF
6. filtrarF
-}

fsVacio :: FileSystem
fsVacio = nuevoF

tema1 :: Tema
tema1 = nuevoT "Cae el sol" "Airbag"

tema2 :: Tema
tema2 = nuevoT "Me gusta" "Ciro y los Persas"

fsFinal :: FileSystem
fsFinal = agregarF tema1 fsVacio

fileSystemTest :: [Bool]
fileSystemTest = [fsVacio == FS [] [],
    etiquetasF (FS ["pop","soul"] []) == ["pop","soul"],
    temasF (FS [] [tema1,tema2]) == [tema1, tema2],
    agregarF tema2 fsFinal == FS [] [tema1, tema2],
    filtrarF "pop" (FS [] [agregarT "pop" tema1, agregarT "soul" tema2]) == [agregarT "pop" tema1]
    ]