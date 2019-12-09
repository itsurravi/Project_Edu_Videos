package com.codrox.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.codrox.myapp.Models.CartItems;
import com.codrox.myapp.Models.SubTopicsInfo;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.Models.VideoLib;

import java.util.ArrayList;
import java.util.List;

public class DB_Handler extends SQLiteOpenHelper {

    public final String TAG = "SQL_Query";

    public static final String DB_NAME = "UserDB";

    /*---------------User Table------------------*/
    public static final String TABLE_USER = "Users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";


    /*---------------User Comments Table-------------*/
    public static final String TABLE_COMMENTS = "Comments";
    public static final String COMMENT_ID = "id";
    public static final String COMMENT_USER_ID = "user_id";
    public static final String COMMENT = "comment";
    public static final String COMMENT_TOPIC_ID = "topic_id";

    /*--------------Chapter Topics Table--------------*/
    public static final String TABLE_TOPIC = "Topic";
    public static final String TOPIC_ID = "topic_id";
    public static final String TOPIC_CLASS = "class";
    public static final String TOPIC_SUBJECT = "subject";
    public static final String TOPIC_CHAPTER = "chapter";
    public static final String TOPIC_NAME = "topic_name";
    public static final String TOPIC_SUBTOPIC_NAME = "sub_topic_name";
    public static final String TOPIC_PRICE = "topic_price";
    public static final String TOPIC_VIDEO_URL = "topic_video_url";

    /*----------------Cart Table-----------------*/
    public static final String TABLE_CART = "Cart";
    public static final String CART_ID = "cart_id";
    public static final String CART_USER_ID = "user_id";
    public static final String CART_TOPIC_ID = "topic_id";

    /*----------------VideoLib Table-------------*/
    public static final String TABLE_VIDEO_LIB = "Video_Library";
    public static final String VIDEO_ID = "video_id";
    public static final String VIDEO_USER_ID = "user_id";
    public static final String VIDEO_TOPIC_ID = "topic_id";

    Context c;

    public DB_Handler(Context context) {
        super(context, DB_NAME, null, 1);
        this.c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "CREATE TABLE " + TABLE_USER
                + "(" + USER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " VARCHAR, "
                + USER_EMAIL + " VARCHAR, "
                + USER_PASSWORD + " VARCHAR);";
        db.execSQL(userTable);

        String userComments = "CREATE TABLE " + TABLE_COMMENTS
                + "(" + COMMENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COMMENT_USER_ID + " VARCHAR, "
                + COMMENT_TOPIC_ID + " VARCHAR, "
                + COMMENT + " VARCHAR);";

        db.execSQL(userComments);

        String topicsTable = "CREATE TABLE " + TABLE_TOPIC
                + "(" + TOPIC_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TOPIC_CLASS + " VARCHAR, "
                + TOPIC_SUBJECT + " VARCHAR, "
                + TOPIC_CHAPTER + " VARCHAR, "
                + TOPIC_NAME + " VARCHAR, "
                + TOPIC_SUBTOPIC_NAME + " VARCHAR, "
                + TOPIC_PRICE + " VARCHAR, "
                + TOPIC_VIDEO_URL + " VARCHAR);";

        db.execSQL(topicsTable);

        String cartTable = "CREATE TABLE " + TABLE_CART
                + "(" + CART_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_USER_ID + " VARCHAR, "
                + CART_TOPIC_ID + " VARCHAR UNIQUE);";
        db.execSQL(cartTable);

        String videoTable = "CREATE TABLE " + TABLE_VIDEO_LIB
                + "(" + VIDEO_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VIDEO_USER_ID + " VARCHAR, "
                + VIDEO_TOPIC_ID + " VARCHAR);";
        db.execSQL(videoTable);


        insertTopics(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private String getVideoUri(String video_name) {
        String packagename = c.getPackageName();
        int resourceid = c.getResources().getIdentifier(video_name, "raw", packagename);
        return String.valueOf(Uri.parse("android.resource://" + packagename + "/" + resourceid));
    }
/*
* + getVideoUri("video") +
* */
    /*-------------------------------Chapter Topics TABLE-----------------------------*/
    private void insertTopics(SQLiteDatabase db) {
        String sql = "INSERT INTO `topic` (`topic_id`, `class`, `subject`, `chapter`, `topic_name`, `sub_topic_name`, `topic_price`, `topic_video_url`) VALUES" +
                "(1, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.1 Laws of Chemical Combination', 'Inroduction to Basic Terms', '15', 'videoname')," +
                "(2, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.1 Laws of Chemical Combination', 'Significant Figures', '15', 'videoname')," +
                "(3, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.1 Laws of Chemical Combination', 'Laws of Chemical Combinations', '10', 'videoname')," +
                "(4, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.2 Mole Concept', 'Emperical and Molecular Formula', '10', 'videoname')," +
                "(5, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.2 Mole Concept', 'Mole Concept', '10', 'videoname')," +
                "(6, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.3 Limiting Reagent', 'Limiting Reagent', '15', 'videoname')," +
                "(7, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.3 Limiting Reagent', 'Numericals on Mole Concept', '12', 'videoname')," +
                "(8, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.4 Measurement of Concentration', 'Concentration Terms', '15', 'videoname')," +
                "(9, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.5 Principle of Atom Conservation', 'Principle of Atom Conservation', '12', 'videoname')," +
                "(10, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.6 Equivalent Concept', 'Equivalent Mass', '15', 'videoname')," +
                "(11, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.6 Equivalent Concept', 'Normality', '12', 'videoname')," +
                "(12, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.6 Equivalent Concept', 'Equivalent Concept', '5', 'videoname')," +
                "(13, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.7 Problems Based on Equivalent Concept', 'Equivalent Concept', '10', 'videoname')," +
                "(14, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.7 Problems Based on Equivalent Concept', 'Titration', '12', 'videoname')," +
                "(15, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.7 Problems Based on Equivalent Concept', 'Percentage of Free SO3 in Oleum', '15', 'videoname')," +
                "(16, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.1 Early Developments', 'Cathode Ray Tube Experiment', '15', 'videoname')," +
                "(17, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.1 Early Developments', 'J.J Thomsons Experiment', '20', 'videoname')," +
                "(18, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.2 Bohrs Quantum Theory', 'Description of Quantum Parameters,Angular Momentum', '15', 'videoname')," +
                "(19, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.2 Bohrs Quantum Theory', 'Energy,Velocity', '20', 'videoname')," +
                "(20, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.3 Hydrogen Emission Spectrum', 'Electromagnetic Waves and Wave Parameters and Planks Law', '20', 'videoname')," +
                "(21, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.3 Hydrogen Emission Spectrum', 'Reydberg Equation,Dual Nature of Matter(De Broglie Relation)', '15', 'videoname')," +
                "(22, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Photoelectric Effect', '10', 'videoname')," +
                "(23, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Quantum Numbers and Electronic Configuration', '10', 'videoname')," +
                "(24, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Heisenbergs Uncertanity Principle', '15', 'videoname')," +
                "(25, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Schrodinger Wave Equation', '20', 'videoname')," +
                "(26, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.1 Early Developments', 'Early Developments,Dobereine Traids', '10', 'videoname')," +
                "(27, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.1 Early Developments', 'Periodic Properties,Types of Atomic Radii', '20', 'videoname')," +
                "(28, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.2 Periodic Properties', 'Screening Effect and Atomic Radius', '20', 'videoname')," +
                "(29, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.2 Periodic Properties', 'Ionization Energy,Electron Affinity,Electronegativity', '15', 'videoname')," +
                "(30, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.1 Introduction to Chemical Bond', 'Types of Chemical Bond', '15', 'videoname')," +
                "(31, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.1 Introduction to Chemical Bond', 'Lewis Dot Structure', '20', 'videoname')," +
                "(32, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.2 Covalent Bonding', 'Ionic Solids', '20', 'videoname')," +
                "(33, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.2 Covalent Bonding', 'Orbital Concept of Covalency', '15', 'videoname')," +
                "(34, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.3 Hybridisation', 'Hybridisation', '20', 'videoname')," +
                "(35, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.3 Hybridisation', 'Geomatry and Shape of Molecule', '15', 'videoname')," +
                "(36, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.4 Dipole Moment', 'Bond Parameters', '12', 'videoname')," +
                "(37, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.4 Dipole Moment', 'Dipole Moment', '15', 'videoname')," +
                "(38, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Formal Charge', '15', 'videoname')," +
                "(39, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Linear Combination of Atomic', '10', 'videoname')," +
                "(40, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Shape of Molecular Orbitals', '10', 'videoname')," +
                "(41, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Molecular Orbital Diagram', '10', 'videoname')," +
                "(42, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Hydrogen Bonding', '15', 'videoname')," +
                "(43, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.1 Gas Laws', 'Intermolecular forces of attraction', '15', 'videoname')," +
                "(44, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.1 Gas Laws', 'Gas Laws', '10', 'videoname')," +
                "(45, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.2 Ideal Gas Equation', 'Ideal Gas Equation', '10', 'videoname')," +
                "(46, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.2 Ideal Gas Equation', 'Daltons Law of Partial Pressures', '15', 'videoname')," +
                "(47, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.2 Ideal Gas Equation', 'Kinetic Theory of Gases', '15', 'videoname')," +
                "(48, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.3 Van der Waals Gas Equation for Real Gases', 'Grahams Law of Diffusion', '20', 'videoname')," +
                "(49, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.3 Van der Waals Gas Equation for Real Gases', 'van der Waals Gas Equation', '20', 'videoname')," +
                "(50, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.3 Van der Waals Gas Equation for Real Gases', 'Compressibility Factor', '15', 'videoname')," +
                "(51, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.4 Liquid State', 'Liquification of Gases', '15', 'videoname')," +
                "(52, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.4 Liquid State', 'Eudiometry', '15', 'videoname')," +
                "(53, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.4 Liquid State', 'Liquid State', '20', 'videoname')," +
                "(54, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.1 Basic Concepts', 'Baisc Concepts and Terms in Thermodynamics', '20', 'videoname')," +
                "(55, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.1 Basic Concepts', 'Internal Energy', '12', 'videoname')," +
                "(56, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.2 Heat parameters & Laws of Heat', 'Laws of thermodynamics & work', '10', 'videoname')," +
                "(57, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.2 Heat parameters & Laws of Heat', 'Heat Parameters', '10', 'videoname')," +
                "(58, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.3 Heat Processes & Entropy', 'Thermal Processes', '15', 'videoname')," +
                "(59, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.3 Heat Processes & Entropy', 'The Concept of Entropy', '15', 'videoname')," +
                "(60, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.4 Second Law of Thermodynamics', 'Second Law of Thermodynamics', '20', 'videoname')," +
                "(61, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.4 Second Law of Thermodynamics', 'Free Energy', '20', 'videoname')," +
                "(62, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.5 Thermochemistry', 'Thermochemistry', '15', 'videoname')," +
                "(63, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.1 Law of Mass Action', 'Law of Mass Action', '10', 'videoname')," +
                "(64, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.1 Law of Mass Action', 'Prediction of Direction of Equilibrium', '15', 'videoname')," +
                "(65, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.2 Equilibrium Constants', 'Kp and Kc', '15', 'videoname')," +
                "(66, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.2 Equilibrium Constants', 'Numericals on Kp and Kc', '15', 'videoname')," +
                "(67, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.3 Le-Chateliers Principle', 'Numericals on Kp and Kc', '10', 'videoname')," +
                "(68, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.3 Le-Chateliers Principle', 'Degree of Dissociation', '15', 'videoname')," +
                "(69, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.3 Le-Chateliers Principle', 'Le-Chateliers Principle', '20', 'videoname')," +
                "(70, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.4 Concept of Acid and Base', 'Concept of Acid and Base', '20', 'videoname')," +
                "(71, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.4 Concept of Acid and Base', 'Ostwalds Dilution Law', '15', 'videoname')," +
                "(72, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'Effect of Temperature', '15', 'videoname')," +
                "(73, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'pH of Strong Acid and Strong Base', '15', 'videoname')," +
                "(74, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'pH of Weak Acid and Weak Base', '20', 'videoname')," +
                "(75, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'pH of Mixtures', '20', 'videoname')," +
                "(76, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'Salt Hydrolysis', '12', 'videoname')," +
                "(77, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.6 Buffer Solution', 'Buffer Solution', '15', 'videoname')," +
                "(78, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.6 Buffer Solution', 'Buffer Capacity', '16', 'videoname')," +
                "(79, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.6 Buffer Solution', 'Indicators', '9', 'videoname')," +
                "(80, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.7 Solubility Product', 'Titration', '10', 'videoname')," +
                "(81, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.7 Solubility Product', 'Solubility Product', '20', 'videoname')," +
                "(82, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.7 Solubility Product', 'Solubility', '20', 'videoname')," +
                "(83, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.1 Oxidation Number', 'Oxidation Number', '10', 'videoname')," +
                "(84, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.1 Oxidation Number', 'Oxidation and Reducing Agent', '15', 'videoname')," +
                "(85, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.2 Balancing of Redox Reaction', 'Balancing of Redox Reactions', '15', 'videoname')," +
                "(86, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.2 Balancing of Redox Reaction', 'Equivalent Weight', '15', 'videoname')," +
                "(87, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.3 Titration', 'Back Titration', '10', 'videoname')," +
                "(88, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.3 Titration', 'Double Titration', '15', 'videoname')," +
                "(89, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.3 Titration', 'Iodometric and Iodimetric Titration', '15', 'videoname')," +
                "(90, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.4 Galvanic Cell', 'Stoichiometry', '20', 'videoname')," +
                "(91, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.4 Galvanic Cell', 'Electrochemical Series', '20', 'videoname')," +
                "(92, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.4 Galvanic Cell', 'Galvanic cell', '10', 'videoname')," +
                "(93, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Position of H2 in Periodic Table', '15', 'videoname')," +
                "(94, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Isotopes and Preparation of Hydrogen', '15', 'videoname')," +
                "(95, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Properties of Hydrogen', '20', 'videoname')," +
                "(96, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Hydrides', '10', 'videoname')," +
                "(97, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.2 Uses of Hydrogen and Water', 'Water', '20', 'videoname')," +
                "(98, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.2 Uses of Hydrogen and Water', 'Properties of Water', '14', 'videoname')," +
                "(99, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.2 Uses of Hydrogen and Water', 'Hardness of Water', '25', 'videoname')," +
                "(100, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.3 Hydrogen Peroxide', 'Preparation', '20', 'videoname')," +
                "(101, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.3 Hydrogen Peroxide', 'Physical Properties', '15', 'videoname')," +
                "(102, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.3 Hydrogen Peroxide', 'Chemical Properties', '12', 'videoname')," +
                "(103, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.1 Group-1 Elements', 'Introduction and General Physical Properties', '15', 'videoname')," +
                "(104, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.1 Group-1 Elements', 'Chemical Properties', '20', 'videoname')," +
                "(105, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.1 Group-1 Elements', 'Compounds of Sodium and Potassium', '20', 'videoname')," +
                "(106, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium,Potassium and Gp-2 Elements', 'Reaction of NaOH', '15', 'videoname')," +
                "(107, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium,Potassium and Gp-2 Elements', 'Sodium Carbonate and Sodium Thiosulphate', '15', 'videoname')," +
                "(108, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium,Potassium and Gp-2 Elements', 'Compounds of Potassium,Abnormal Behaviour of Lithium', '20', 'videoname')," +
                "(109, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium,Potassium and Gp-2 Elements', 'General Physical Properties of Group-2 Elements', '15', 'videoname')," +
                "(110, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Effect of Air,Water and H2', '15', 'videoname')," +
                "(111, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Halides,Nitrates,Sulphates Carbonates and Bicarbonates', '20', 'videoname')," +
                "(112, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Effect of Liquid NH3 and Diagonal Relationship', '12', 'videoname')," +
                "(113, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Effect of Carbon and Oxides and Sulphates of Ca and Mg', '20', 'videoname')," +
                "(114, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'Introduction,Inert pair Effect', '15', 'videoname')," +
                "(115, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'General Chemical Properties', '20', 'videoname')," +
                "(116, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'Some more Chemical Properties', '20', 'videoname')," +
                "(117, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'Extraction of Boron from its Ores', '', 'videoname')," +
                "(118, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Electrolytic Method for Extraction of B', '20', 'videoname')," +
                "(119, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Diborane and Borazole', '25', 'videoname')," +
                "(120, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Boron Nitride and Some of the Compounds of Aluminium', '15', 'videoname')," +
                "(121, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Group-14 Elements', '20', 'videoname')," +
                "(122, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Compounds of Carbon', '12', 'videoname')," +
                "(123, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Halides,Oxides of Group-14 Elements,Allotropes of Carbon', '10', 'videoname')," +
                "(124, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Carbides and Carbon Monoxide', '15', 'videoname')," +
                "(125, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Silicon', '20', 'videoname')," +
                "(126, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Bonding and Hybridisation of Organic Compounds', '15', 'videoname')," +
                "(127, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Nomenclature of Hydrocarbons', '15', 'videoname')," +
                "(128, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Nomenclature of Monofunctional Compounds ', '20', 'videoname')," +
                "(129, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Nomenclature of Polyfunctional Compounds', '15', 'videoname')," +
                "(130, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.2 Isomerism in Organic Compounds', 'Structural isomerism', '20', 'videoname')," +
                "(131, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.2 Isomerism in Organic Compounds', 'Stereoisomerism', '22', 'videoname')," +
                "(132, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.3 Electronic Effects', 'Fission of Bonds', '21', 'videoname')," +
                "(133, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.3 Electronic Effects', 'Electronic Effects', '10', 'videoname')," +
                "(134, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.4 Aromaticity', 'Resonance Effect', '8', 'videoname')," +
                "(135, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.4 Aromaticity', 'Hyperconjugation', '15', 'videoname')," +
                "(136, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.4 Aromaticity', 'Aromaticity', '20', 'videoname')," +
                "(137, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.5 Intermediate Compounds', 'Tautomerism', '20', 'videoname')," +
                "(138, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.5 Intermediate Compounds', 'Intermediate Compounds', '15', 'videoname')," +
                "(139, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Addition Reactions', '20', 'videoname')," +
                "(140, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Substitution Reactions', '15', 'videoname')," +
                "(141, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Elimination Reactions', '14', 'videoname')," +
                "(142, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Rearrangements', '10', 'videoname')," +
                "(143, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.7 Purification of Organic Compounds', 'Distillation', '8', 'videoname')," +
                "(144, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.7 Purification of Organic Compounds', 'Chromatography', '7', 'videoname')," +
                "(145, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.1 Alkanes', 'Preparation of Alkanes', '15', 'videoname')," +
                "(146, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.1 Alkanes', 'Physical Properties of Alkanes', '20', 'videoname')," +
                "(147, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.1 Alkanes', 'Chemical Properties of Alkanes', '12', 'videoname')," +
                "(148, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.2 Alkenes', 'Preparation of Alkenes', '12', 'videoname')," +
                "(149, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.2 Alkenes', 'Physical Properties of Alkenes', '20', 'videoname')," +
                "(150, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.2 Alkenes', 'Chemical Properties of Alkenes', '20', 'videoname')," +
                "(151, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.3 Alkenes and Alkynes ', 'Chemical Properties of Alkenes', '20', 'videoname')," +
                "(152, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.3 Alkenes and Alkynes ', 'Structure of Alkynes', '20', 'videoname')," +
                "(153, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.3 Alkenes and Alkynes ', 'Preparation of Alkynes', '20', 'videoname')," +
                "(154, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Aromaticity', '12', 'videoname')," +
                "(155, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Electrophillic Aromatic Substitution', '15', 'videoname')," +
                "(156, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Orientation in Disubstituted Rings', '15', 'videoname')," +
                "(157, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Preparation of Benzene', '16', 'videoname')," +
                "(158, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Chemical Properties of Benzene', '14', 'videoname')," +
                "(159, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Chemical Properties of Toulene', '12', 'videoname')," +
                "(160, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Ozonolysis of Aromatic Hydrocarbons', '10', 'videoname')," +
                "(161, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.5 Alkynes', 'Chemical Properties of Alkynes', '10', 'videoname')," +
                "(162, 'Class 11', 'Chemistry', '14. ENVIRONMENTAL CHEMISTRY', '14.1 Environmental Chemistry', 'Air Pollution,Introduction and Pollutant', '15', 'videoname')," +
                "(163, 'Class 11', 'Chemistry', '14. ENVIRONMENTAL CHEMISTRY', '14.1 Environmental Chemistry', 'Oxides of C,N,Acid Rain,Particulate Matter', '20', 'videoname')," +
                "(164, 'Class 11', 'Chemistry', '14. ENVIRONMENTAL CHEMISTRY', '14.1 Environmental Chemistry', 'Water Pollution,Soil Pollution', '15', 'videoname');";

        db.execSQL(sql);

        Log.d(TAG, "Command Inserted");
    }

    public List<String> getChaptersList(String className, String subject) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT DISTINCT " + TOPIC_CHAPTER + " FROM " + TABLE_TOPIC + " WHERE "
                + TOPIC_CLASS + "='" + className + "' AND " + TOPIC_SUBJECT + "='" + subject + "';";
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {

                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));

                list.add(t_chapter);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }

    public List<TopicsInfo> getTopicTitleList(String className, String subject, String chapter) {
        List<TopicsInfo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT DISTINCT " + TOPIC_NAME + " FROM " + TABLE_TOPIC + " WHERE "
                + TOPIC_CLASS + "='" + className + "' AND "
                + TOPIC_CHAPTER + "='" + chapter + "' AND "
                + TOPIC_SUBJECT + "='" + subject + "';";

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {

                String t_chapter = c.getString(c.getColumnIndex(TOPIC_NAME));

                List<SubTopicsInfo> dt = getSubTopicsList(className,subject,chapter, t_chapter);

                TopicsInfo info=new TopicsInfo(t_chapter,dt);

                list.add(info);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }

    public List<SubTopicsInfo> getSubTopicsList(String className, String subject, String chapter, String topic) {
        List<SubTopicsInfo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_TOPIC + " WHERE " + TOPIC_CLASS + "='" + className + "' AND "
                + TOPIC_SUBJECT + "='" + subject + "' AND " + TOPIC_NAME + "='" + topic + "' AND "
                + TOPIC_CHAPTER + "='" + chapter + "';";

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String t_id = c.getString(c.getColumnIndex(TOPIC_ID));
                String t_class = c.getString(c.getColumnIndex(TOPIC_CLASS));
                String t_sub = c.getString(c.getColumnIndex(TOPIC_SUBJECT));
                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));
                String t_name = c.getString(c.getColumnIndex(TOPIC_NAME));
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_SUBTOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));
                String t_url = c.getString(c.getColumnIndex(TOPIC_VIDEO_URL));

                SubTopicsInfo info = new SubTopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_s_name, t_price, t_url);

                list.add(info);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;

    }


    /*-------------------------------USER TABLE---------------------------------------*/
    public boolean registerUser(String name, String uname, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, name);
        cv.put(USER_EMAIL, uname);
        cv.put(USER_PASSWORD, pswd);

        db.insert(TABLE_USER, null, cv);
        db.close();

        return true;
    }

    public boolean updateUserName(String id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, name);

        int res = db.update(TABLE_USER, cv, "id=?", new String[]{id});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserEmail(String id, String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_EMAIL, uname);

        int res = db.update(TABLE_USER, cv, "id=?", new String[]{id});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserPassword(String id, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_PASSWORD, pswd);

        int res = db.update(TABLE_USER, cv, "id=?", new String[]{id});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor signInUserWithEmailAndPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_EMAIL + "='" + email + "' AND " + USER_PASSWORD + "='" + password + "';";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUserData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + "=" + id + ";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public int getUserId(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_EMAIL + "='" + email + "';";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        String id = c.getString(c.getColumnIndex(DB_Handler.USER_ID));

        return Integer.parseInt(id);
    }


    /*------------------------------USER COMMENT--------------------------------------*/
    public void saveComment(String id, String comment, String topic_id  /*need to add video id here*/) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COMMENT_USER_ID, id);
        cv.put(COMMENT, comment);
        cv.put(COMMENT_TOPIC_ID, topic_id);

        db.insert(TABLE_COMMENTS, null, cv);
        db.close();
    }

    public List<String> getComments(String id, String topic_id  /*need to add video id here*/) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENT_USER_ID + "='" + id + "' AND " + COMMENT_TOPIC_ID + "='" + topic_id + "';";
//        String sql = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENT_USER_ID + "='" + id + "';";
        Cursor c = db.rawQuery(sql, null);
        List<String> l = new ArrayList<>();
        try {
            if (c != null) {
                c.moveToFirst();
                do {
                    l.add(c.getString(c.getColumnIndex(COMMENT)));
                }
                while (c.moveToNext());
                c.close();
            }
            return l;
        } catch (Exception e) {
            return null;
        }
    }


    /*------------------------------CART TABLE-----------------------------------------*/
    public void saveToCart(String id, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CART_TOPIC_ID, id);
        cv.put(CART_USER_ID, user_id);

        db.insert(TABLE_CART, null, cv);
        db.close();
    }

    public void removeToCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, CART_ID + "=?", new String[]{id});
    }

    public List<CartItems> getAllCartItems(String user_id) {
        List<CartItems> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + TABLE_CART + "." + CART_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_CLASS + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBJECT + ", "
                + TABLE_TOPIC + "." + TOPIC_CHAPTER + ", "
                + TABLE_TOPIC + "." + TOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBTOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_VIDEO_URL + ", "
                + TABLE_TOPIC + "." + TOPIC_PRICE + " FROM "
                + TABLE_CART + " INNER JOIN " + TABLE_TOPIC + " ON "
                + TABLE_CART + "." + CART_TOPIC_ID + "=" + TABLE_TOPIC + "." + TOPIC_ID + " WHERE "
                + TABLE_CART + "." + CART_USER_ID + "='" + user_id + "';";

        Log.d(TAG, "All Cart SQL=> " + sql);

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex(CART_ID));

                String t_id = c.getString(c.getColumnIndex(TOPIC_ID));
                String t_class = c.getString(c.getColumnIndex(TOPIC_CLASS));
                String t_sub = c.getString(c.getColumnIndex(TOPIC_SUBJECT));
                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));
                String t_name = c.getString(c.getColumnIndex(TOPIC_NAME));
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_SUBTOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));
                String t_url = c.getString(c.getColumnIndex(TOPIC_VIDEO_URL));

                SubTopicsInfo info = new SubTopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_s_name, t_price, t_url);
                CartItems items = new CartItems(id, info);

                list.add(items);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }


    /*-----------------------------VIDEO LIBRARY TABLE---------------------------------*/
    public void saveToLibrary(String id, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(VIDEO_TOPIC_ID, id);
        cv.put(VIDEO_USER_ID, user_id);

        db.insert(TABLE_VIDEO_LIB, null, cv);
        db.close();
    }

    public List<VideoLib> getSavedVideos(String user_id) {
        List<VideoLib> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + TABLE_VIDEO_LIB + "." + VIDEO_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_CLASS + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBJECT + ", "
                + TABLE_TOPIC + "." + TOPIC_CHAPTER + ", "
                + TABLE_TOPIC + "." + TOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBTOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_VIDEO_URL + ", "
                + TABLE_TOPIC + "." + TOPIC_PRICE + " FROM "
                + TABLE_VIDEO_LIB + " INNER JOIN " + TABLE_TOPIC + " ON "
                + TABLE_VIDEO_LIB + "." + VIDEO_TOPIC_ID + "=" + TABLE_TOPIC + "." + TOPIC_ID + " WHERE "
                + TABLE_VIDEO_LIB + "." + VIDEO_USER_ID + "='" + user_id + "';";

        Log.d(TAG, "All Cart SQL=> " + sql);

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex(VIDEO_ID));
                String t_id = c.getString(c.getColumnIndex(TOPIC_ID));
                String t_class = c.getString(c.getColumnIndex(TOPIC_CLASS));
                String t_sub = c.getString(c.getColumnIndex(TOPIC_SUBJECT));
                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));
                String t_name = c.getString(c.getColumnIndex(TOPIC_NAME));
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_SUBTOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));

                String t_url = c.getString(c.getColumnIndex(TOPIC_VIDEO_URL));

                SubTopicsInfo info = new SubTopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_s_name, t_price, t_url);
                VideoLib lib = new VideoLib(id, info);

                list.add(lib);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }
}
