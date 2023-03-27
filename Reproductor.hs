module Reproductor ( Reproductor, nuevoR, archivosR, listaParaR, temasR, playR, actualR, avanzarR, retrocederR, reiniciarR)
    where

import Tipos
import Tema
import Playlist
import FileSystem

data Reproductor = RP FileSystem Playlist deriving (Eq, Show)

nuevoR :: FileSystem -> Reproductor
nuevoR fs = RP fs (nuevaP [])

archivosR :: Reproductor -> FileSystem
archivosR (RP fs _) = fs

listaParaR :: Etiqueta -> Reproductor -> [ Tema ]
listaParaR eti (RP fs _) = filtrarF eti fs

temasR :: Reproductor -> [ Tema ]
temasR (RP fs _) = temasF fs

playR :: Reproductor -> Etiqueta -> Reproductor
playR (RP fs playlist) eti = RP fs (nuevaP (listaParaR eti (RP fs playlist)))

actualR :: Reproductor -> Tema
actualR (RP _ playlist) = actualP playlist

avanzarR :: Reproductor -> Reproductor
avanzarR (RP fs playlist) = RP fs (skipP playlist)

retrocederR :: Reproductor -> Reproductor
retrocederR (RP fs playlist) = RP fs (backP playlist)

reiniciarR :: Reproductor -> Reproductor
reiniciarR (RP fs playlist) = RP fs (resetP playlist)

{- TEST
1. nuevoR
2. archivosR
3. listaParar
4. temasR
5. playR
6. actualR
7. avanzarR
8. retrocederR
9. reiniciarR
-}

fsVacio :: FileSystem
fsVacio = nuevoF

tBase :: Tema
tBase = nuevoT "nombre" "dato"

tema1 :: Tema
tema1 = agregarT "funk" tBase

tema2 :: Tema
tema2 = agregarT "R&B" tBase

fs1 :: FileSystem
fs1 = agregarF tema1 fsVacio

fsFinal :: FileSystem
fsFinal = agregarF tema2 fs1

playlist :: Playlist
playlist = nuevaP [tema1, tema2]

rBase :: Reproductor
rBase = nuevoR fsVacio

reproductor1 :: Reproductor
reproductor1 = nuevoR fsFinal

reproductor2 :: Reproductor
reproductor2 = RP fsFinal playlist

reproductorTest :: [Bool]
reproductorTest = [rBase == RP fsVacio (nuevaP []),
    archivosR reproductor1 == fsFinal,
    listaParaR "funk" reproductor1 == [tema1],
    temasR reproductor1 == [tema2, tema1],
    playR reproductor2 "R&B" == RP fsFinal (nuevaP [tema2]),
    actualR reproductor2 == tema1,
    avanzarR reproductor2 == RP fsFinal (skipP playlist),
    retrocederR reproductor2 == RP fsFinal (backP playlist),
    reiniciarR reproductor2 == RP fsFinal playlist
    ]