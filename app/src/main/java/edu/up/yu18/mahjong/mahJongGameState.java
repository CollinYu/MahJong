package edu.up.yu18.mahjong;

private class mahJongGameState{
    private ArrayList<Tile> deck;
    private int[] wall;
    private int[] discardPile;
    private int[] players;
    private String[] playerNames;
    // Note that the int arrays below are [playerID][tileID]
    private int[][] playerClosedHands;
    private int[][] playerOpenHands;
    // whose turn it is will be expressed in stages, player 1's turn
    //will be stage 1, player 1's post-discard phase will be stage 2,
    // player 2's turn will be stage 3, her discard phase will be stage 4,
    //and so forth, resulting in the game being split up into stages 1 through 8
    private int gameStage;


    //Constructor
    private mahJongGameState(){
        wall = new int[game.wall.length];
        discardPile = new int[game.discardPile.length];
        players = new int[players.length];
        playerNames = new String[game.playerNames.length];
        playerClosedHands = new int [game.playerClosedHands.length][];
        playerOpenHands = new int [game.playerOpenHands.length][];
    }

    public void setPlayerName(String name)
    {
        this.playerNames = name;
    }

    public String setPlayerName(String name)
    {
        return this.playerNames;
    }

    // Takes an existing mahJongGameState object as a parameter to
    // initialize a new object with the same attributes
    public mahJongGameState(mahJongGameState game){

        // Goes through each element of int[] wall and makes it individually
        // a copy of the equivalent element of the parameter
        wall = new int[game.wall.length];
        for (int i = 0; i < game.wall.length; i++){
            wall[i] = game.wall[i];
        }

        discardPile = new int[game.discardPile.length];
        for (int i = 0; i < game.discardPile.length; i++){
            discardPile[i] = game.discardPile[i];
        }

        players = new int[players.length];
        for (int i = 0; i < game.players.length; i++){
            players[i] = game.players[i];
        }

        playerNames = new String[game.playerNames.length];
        for (int i = 0; i < game.playerNames.length; i++){
            playerNames[i] = game.playerNames[i];
        }


        playerClosedHands = new int [game.playerClosedHands.length][]; // sets the "array of arrays" to the equivalent size - playerClosedHands[copies this part's length][]
        for(int i = 0; i < game.playerClosedHands.length; i++){ // goes through each array in the "array of arrays" - playerClosedHands[goes through this part][]
            playerClosedHands[i] = new int[game.playerClosedHands.length]; // sets array length to that of the equivalent array - playerClosedHands[][copies this part's length]

            for (int j = 0; j < game.playerClosedHands[i].length; j++){ // goes through each element of the array in question - playerClosedHands[][goes through his part]
                playerClosedHands[i][j] = game.playerClosedHands[i][j]; // sets the element to be the same as the equivalent element - playerClosedHands[][copies this part]
            }
        }

        playerOpenHands = new int [game.playerOpenHands.length][];
        for(int i = 0; i < game.playerOpenHands.length; i++){
            playerOpenHands[i] = new int[game.playerOpenHands.length];

            for (int j = 0; j < game.playerOpenHands[i].length; j++){
                playerOpenHands[i][j] = game.playerOpenHands[i][j];
            }
        }

        gameStage = game.gameStage; // pretty obvious how this one works

    }


}