module Playlist (Playlist, nuevaP, actualP, skipP, backP, resetP)
    where

import Tipos
import Tema

data Playlist = Play Int [ Tema ] deriving (Eq, Show)

nuevaP :: [ Tema ] -> Playlist
nuevaP = Play 0

actualP :: Playlist -> Tema
actualP (Play i temas) = temas !! i

skipP :: Playlist -> Playlist
skipP (Play i temas) = Play (i + 1) temas

backP :: Playlist -> Playlist
backP (Play i temas) = Play (i - 1) temas

resetP :: Playlist -> Playlist
resetP (Play i temas) = Play 0 temas

{- TEST
1. nuevaP con lista vacía
2. nuevaP con lista NO vacía
3. actualP
4. skipP
5. backP
6. resetP cuando el indice ya está en 0
7. resetP cuando el indidce NO está en 0
-}

base :: Tema
base = nuevoT "nombre" "dato"

primerT :: Tema
primerT = agregarT "trap" base

segundoT :: Tema
segundoT = agregarT "pop" base

playlist :: Playlist
playlist = nuevaP [primerT, segundoT]

playlistTest :: [Bool]
playlistTest = [ nuevaP [] == Play 0 [],
    playlist == Play 0 [primerT, segundoT],
    actualP playlist == primerT,
    skipP playlist == Play 1 [primerT, segundoT],
    backP (Play 1 [primerT, segundoT]) == playlist,
    resetP playlist == playlist,
    resetP (Play 1 [primerT, segundoT]) == playlist
    ]