/***********************
 ****** LANCEMENT ******
 ***********************/

Pour lancer l'application, il suffit de cliquer sur le fichier .jar à la racine
du dossier.
Les ressources du logiciel se trouvent à l'intérieur du projet, dans les
packages "resources". Il y en a un dans la vue, pour les icones, etc, et un dans
le controleur, pour le graphe par défaut.

/***********************
 **** CONFIGURATION ****
 ***********************/

La configuration est définie lors de la pré-compilation du logiciel.
Toutes ces configurations se trouvent au niveau du point d'entré du programme :
"Main.java".

Il existe plusieurs paramètres de configuration :
 * Le chemin d'accès aux fichiers
   ex : ImageLoader.setImageFolder("D:\\Images");
 * Le graphe à charger par défaut
   ex : graphFactory.load(resourceLoader.loadStream("mapsixieme.xml"));
 * L'algorithme de plus court chemin
   ex : new MainActionManager(..., new Astar(new BirdFly()), ...);
   On défini l'algorithme dans le constructeur du controleur principal car
   c'est lui qui l'attribuera aux robots nouvellement créés.
 * Le ou les types de manager à utiliser.
   ex : new MainActionManager(..., new Manager[] { new FireFighterManager(graph) });
   Il ne faut pas oublier de les attribuer au RobotRuntime pour qu'ils soient
   mis à jour régulièrement.
 * Changer la vitesse de mise à jour des managers
   Ligne 94 dans "MainActionManager.java", runtime.start(TIME);


Pour charger un nouveau graphe, il suffit de l'ouvrir à partir de l'interface
homme-machine lorsque le logiciel est lancé. La première fenêtre demande un
fichier .xml et la seconde un fichier .jpg pour l'image de fond. Si il n'y a pas
d'image de fond, alors le graphe sera chargé et affiché nu.