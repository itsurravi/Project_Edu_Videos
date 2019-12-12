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
                "(1, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.1 Laws of Chemical Combination', 'Inroduction to Basic Terms', '15', '"+ getVideoUri("video") +"')," +
                "(2, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.1 Laws of Chemical Combination', 'Significant Figures', '15', '"+ getVideoUri("video") +"')," +
                "(3, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.1 Laws of Chemical Combination', 'Laws of Chemical Combinations', '10', '"+ getVideoUri("video") +"')," +
                "(4, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.2 Mole Concept', 'Emperical and Molecular Formula', '10', '"+ getVideoUri("video") +"')," +
                "(5, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.2 Mole Concept', 'Mole Concept', '10', '"+ getVideoUri("video") +"')," +
                "(6, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.3 Limiting Reagent', 'Limiting Reagent', '15', '"+ getVideoUri("video") +"')," +
                "(7, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.3 Limiting Reagent', 'Numericals on Mole Concept', '12', '"+ getVideoUri("video") +"')," +
                "(8, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.4 Measurement of Concentration', 'Concentration Terms', '15', '"+ getVideoUri("video") +"')," +
                "(9, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.5 Principle of Atom Conservation', 'Principle of Atom Conservation', '12', '"+ getVideoUri("video") +"')," +
                "(10, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.6 Equivalent Concept', 'Equivalent Mass', '15', '"+ getVideoUri("video") +"')," +
                "(11, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.6 Equivalent Concept', 'Normality', '12', '"+ getVideoUri("video") +"')," +
                "(12, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.6 Equivalent Concept', 'Equivalent Concept', '5', '"+ getVideoUri("video") +"')," +
                "(13, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.7 Problems Based on Equivalent Concept', 'Equivalent Concept', '10', '"+ getVideoUri("video") +"')," +
                "(14, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.7 Problems Based on Equivalent Concept', 'Titration', '12', '"+ getVideoUri("video") +"')," +
                "(15, 'Class 11', 'Chemistry', '1. SOME BASIC CONCEPT OF CHEMISTRY', '1.7 Problems Based on Equivalent Concept', 'Percentage of Free SO3 in Oleum', '15', '"+ getVideoUri("video") +"')," +
                "(16, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.1 Early Developments', 'Cathode Ray Tube Experiment', '15', '"+ getVideoUri("video") +"')," +
                "(17, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.1 Early Developments', 'J.J Thomsons Experiment', '20', '"+ getVideoUri("video") +"')," +
                "(18, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.2 Bohrs Quantum Theory', 'Description of Quantum Parameters Angular Momentum', '15', '"+ getVideoUri("video") +"')," +
                "(19, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.2 Bohrs Quantum Theory', 'Energy Velocity', '20', '"+ getVideoUri("video") +"')," +
                "(20, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.3 Hydrogen Emission Spectrum', 'Electromagnetic Waves and Wave Parameters and Planks Law', '20', '"+ getVideoUri("video") +"')," +
                "(21, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.3 Hydrogen Emission Spectrum', 'Reydberg Equation Dual Nature of Matter(De Broglie Relation)', '15', '"+ getVideoUri("video") +"')," +
                "(22, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Photoelectric Effect', '10', '"+ getVideoUri("video") +"')," +
                "(23, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Quantum Numbers and Electronic Configuration', '10', '"+ getVideoUri("video") +"')," +
                "(24, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Heisenbergs Uncertanity Principle', '15', '"+ getVideoUri("video") +"')," +
                "(25, 'Class 11', 'Chemistry', '2. STRUCTURE OF ATOM', '2.4 Dual Nature of Radiation', 'Schrodinger Wave Equation', '20', '"+ getVideoUri("video") +"')," +
                "(26, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.1 Early Developments', 'Early Developments Dobereine Traids', '10', '"+ getVideoUri("video") +"')," +
                "(27, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.1 Early Developments', 'Periodic Properties Types of Atomic Radii', '20', '"+ getVideoUri("video") +"')," +
                "(28, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.2 Periodic Properties', 'Screening Effect and Atomic Radius', '20', '"+ getVideoUri("video") +"')," +
                "(29, 'Class 11', 'Chemistry', '3. CLASSIFICATION OF ELEMENTS AND PERIODICITY IN PROPERTIES', '3.2 Periodic Properties', 'Ionization Energy Electron Affinity Electronegativity', '15', '"+ getVideoUri("video") +"')," +
                "(30, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.1 Introduction to Chemical Bond', 'Types of Chemical Bond', '15', '"+ getVideoUri("video") +"')," +
                "(31, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.1 Introduction to Chemical Bond', 'Lewis Dot Structure', '20', '"+ getVideoUri("video") +"')," +
                "(32, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.2 Covalent Bonding', 'Ionic Solids', '20', '"+ getVideoUri("video") +"')," +
                "(33, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.2 Covalent Bonding', 'Orbital Concept of Covalency', '15', '"+ getVideoUri("video") +"')," +
                "(34, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.3 Hybridisation', 'Hybridisation', '20', '"+ getVideoUri("video") +"')," +
                "(35, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.3 Hybridisation', 'Geomatry and Shape of Molecule', '15', '"+ getVideoUri("video") +"')," +
                "(36, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.4 Dipole Moment', 'Bond Parameters', '12', '"+ getVideoUri("video") +"')," +
                "(37, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.4 Dipole Moment', 'Dipole Moment', '15', '"+ getVideoUri("video") +"')," +
                "(38, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Formal Charge', '15', '"+ getVideoUri("video") +"')," +
                "(39, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Linear Combination of Atomic', '10', '"+ getVideoUri("video") +"')," +
                "(40, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Shape of Molecular Orbitals', '10', '"+ getVideoUri("video") +"')," +
                "(41, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Molecular Orbital Diagram', '10', '"+ getVideoUri("video") +"')," +
                "(42, 'Class 11', 'Chemistry', '4. CHEMICAL BONDING & MOLECULAR STRUCTURE', '4.5 Molecular Orbital Theory', 'Hydrogen Bonding', '15', '"+ getVideoUri("video") +"')," +
                "(43, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.1 Gas Laws', 'Intermolecular forces of attraction', '15', '"+ getVideoUri("video") +"')," +
                "(44, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.1 Gas Laws', 'Gas Laws', '10', '"+ getVideoUri("video") +"')," +
                "(45, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.2 Ideal Gas Equation', 'Ideal Gas Equation', '10', '"+ getVideoUri("video") +"')," +
                "(46, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.2 Ideal Gas Equation', 'Daltons Law of Partial Pressures', '15', '"+ getVideoUri("video") +"')," +
                "(47, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.2 Ideal Gas Equation', 'Kinetic Theory of Gases', '15', '"+ getVideoUri("video") +"')," +
                "(48, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.3 Van der Waals Gas Equation for Real Gases', 'Grahams Law of Diffusion', '20', '"+ getVideoUri("video") +"')," +
                "(49, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.3 Van der Waals Gas Equation for Real Gases', 'van der Waals Gas Equation', '20', '"+ getVideoUri("video") +"')," +
                "(50, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.3 Van der Waals Gas Equation for Real Gases', 'Compressibility Factor', '15', '"+ getVideoUri("video") +"')," +
                "(51, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.4 Liquid State', 'Liquification of Gases', '15', '"+ getVideoUri("video") +"')," +
                "(52, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.4 Liquid State', 'Eudiometry', '15', '"+ getVideoUri("video") +"')," +
                "(53, 'Class 11', 'Chemistry', '5. STATES OF MATTER', '5.4 Liquid State', 'Liquid State', '20', '"+ getVideoUri("video") +"')," +
                "(54, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.1 Basic Concepts', 'Baisc Concepts and Terms in Thermodynamics', '20', '"+ getVideoUri("video") +"')," +
                "(55, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.1 Basic Concepts', 'Internal Energy', '12', '"+ getVideoUri("video") +"')," +
                "(56, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.2 Heat parameters & Laws of Heat', 'Laws of thermodynamics & work', '10', '"+ getVideoUri("video") +"')," +
                "(57, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.2 Heat parameters & Laws of Heat', 'Heat Parameters', '10', '"+ getVideoUri("video") +"')," +
                "(58, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.3 Heat Processes & Entropy', 'Thermal Processes', '15', '"+ getVideoUri("video") +"')," +
                "(59, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.3 Heat Processes & Entropy', 'The Concept of Entropy', '15', '"+ getVideoUri("video") +"')," +
                "(60, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.4 Second Law of Thermodynamics', 'Second Law of Thermodynamics', '20', '"+ getVideoUri("video") +"')," +
                "(61, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.4 Second Law of Thermodynamics', 'Free Energy', '20', '"+ getVideoUri("video") +"')," +
                "(62, 'Class 11', 'Chemistry', '6. THERMODYNAMICS', '6.5 Thermochemistry', 'Thermochemistry', '15', '"+ getVideoUri("video") +"')," +
                "(63, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.1 Law of Mass Action', 'Law of Mass Action', '10', '"+ getVideoUri("video") +"')," +
                "(64, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.1 Law of Mass Action', 'Prediction of Direction of Equilibrium', '15', '"+ getVideoUri("video") +"')," +
                "(65, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.2 Equilibrium Constants', 'Kp and Kc', '15', '"+ getVideoUri("video") +"')," +
                "(66, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.2 Equilibrium Constants', 'Numericals on Kp and Kc', '15', '"+ getVideoUri("video") +"')," +
                "(67, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.3 Le-Chateliers Principle', 'Numericals on Kp and Kc', '10', '"+ getVideoUri("video") +"')," +
                "(68, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.3 Le-Chateliers Principle', 'Degree of Dissociation', '15', '"+ getVideoUri("video") +"')," +
                "(69, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.3 Le-Chateliers Principle', 'Le-Chateliers Principle', '20', '"+ getVideoUri("video") +"')," +
                "(70, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.4 Concept of Acid and Base', 'Concept of Acid and Base', '20', '"+ getVideoUri("video") +"')," +
                "(71, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.4 Concept of Acid and Base', 'Ostwalds Dilution Law', '15', '"+ getVideoUri("video") +"')," +
                "(72, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'Effect of Temperature', '15', '"+ getVideoUri("video") +"')," +
                "(73, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'pH of Strong Acid and Strong Base', '15', '"+ getVideoUri("video") +"')," +
                "(74, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'pH of Weak Acid and Weak Base', '20', '"+ getVideoUri("video") +"')," +
                "(75, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'pH of Mixtures', '20', '"+ getVideoUri("video") +"')," +
                "(76, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.5 pH Value', 'Salt Hydrolysis', '12', '"+ getVideoUri("video") +"')," +
                "(77, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.6 Buffer Solution', 'Buffer Solution', '15', '"+ getVideoUri("video") +"')," +
                "(78, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.6 Buffer Solution', 'Buffer Capacity', '16', '"+ getVideoUri("video") +"')," +
                "(79, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.6 Buffer Solution', 'Indicators', '9', '"+ getVideoUri("video") +"')," +
                "(80, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.7 Solubility Product', 'Titration', '10', '"+ getVideoUri("video") +"')," +
                "(81, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.7 Solubility Product', 'Solubility Product', '20', '"+ getVideoUri("video") +"')," +
                "(82, 'Class 11', 'Chemistry', '7. EQUILIBRIUM', '7.7 Solubility Product', 'Solubility', '20', '"+ getVideoUri("video") +"')," +
                "(83, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.1 Oxidation Number', 'Oxidation Number', '10', '"+ getVideoUri("video") +"')," +
                "(84, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.1 Oxidation Number', 'Oxidation and Reducing Agent', '15', '"+ getVideoUri("video") +"')," +
                "(85, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.2 Balancing of Redox Reaction', 'Balancing of Redox Reactions', '15', '"+ getVideoUri("video") +"')," +
                "(86, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.2 Balancing of Redox Reaction', 'Equivalent Weight', '15', '"+ getVideoUri("video") +"')," +
                "(87, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.3 Titration', 'Back Titration', '10', '"+ getVideoUri("video") +"')," +
                "(88, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.3 Titration', 'Double Titration', '15', '"+ getVideoUri("video") +"')," +
                "(89, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.3 Titration', 'Iodometric and Iodimetric Titration', '15', '"+ getVideoUri("video") +"')," +
                "(90, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.4 Galvanic Cell', 'Stoichiometry', '20', '"+ getVideoUri("video") +"')," +
                "(91, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.4 Galvanic Cell', 'Electrochemical Series', '20', '"+ getVideoUri("video") +"')," +
                "(92, 'Class 11', 'Chemistry', '8. REDOX REACTION', '8.4 Galvanic Cell', 'Galvanic cell', '10', '"+ getVideoUri("video") +"')," +
                "(93, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Position of H2 in Periodic Table', '15', '"+ getVideoUri("video") +"')," +
                "(94, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Isotopes and Preparation of Hydrogen', '15', '"+ getVideoUri("video") +"')," +
                "(95, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Properties of Hydrogen', '20', '"+ getVideoUri("video") +"')," +
                "(96, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.1 Hydrogena as an Element', 'Hydrides', '10', '"+ getVideoUri("video") +"')," +
                "(97, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.2 Uses of Hydrogen and Water', 'Water', '20', '"+ getVideoUri("video") +"')," +
                "(98, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.2 Uses of Hydrogen and Water', 'Properties of Water', '14', '"+ getVideoUri("video") +"')," +
                "(99, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.2 Uses of Hydrogen and Water', 'Hardness of Water', '25', '"+ getVideoUri("video") +"')," +
                "(100, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.3 Hydrogen Peroxide', 'Preparation', '20', '"+ getVideoUri("video") +"')," +
                "(101, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.3 Hydrogen Peroxide', 'Physical Properties', '15', '"+ getVideoUri("video") +"')," +
                "(102, 'Class 11', 'Chemistry', '9. HYDROGEN', '9.3 Hydrogen Peroxide', 'Chemical Properties', '12', '"+ getVideoUri("video") +"')," +
                "(103, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.1 Group-1 Elements', 'Introduction and General Physical Properties', '15', '"+ getVideoUri("video") +"')," +
                "(104, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.1 Group-1 Elements', 'Chemical Properties', '20', '"+ getVideoUri("video") +"')," +
                "(105, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.1 Group-1 Elements', 'Compounds of Sodium and Potassium', '20', '"+ getVideoUri("video") +"')," +
                "(106, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium Potassium and Gp-2 Elements', 'Reaction of NaOH', '15', '"+ getVideoUri("video") +"')," +
                "(107, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium Potassium and Gp-2 Elements', 'Sodium Carbonate and Sodium Thiosulphate', '15', '"+ getVideoUri("video") +"')," +
                "(108, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium Potassium and Gp-2 Elements', 'Compounds of Potassium Abnormal Behaviour of Lithium', '20', '"+ getVideoUri("video") +"')," +
                "(109, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.2 Compounds of Sodium Potassium and Gp-2 Elements', 'General Physical Properties of Group-2 Elements', '15', '"+ getVideoUri("video") +"')," +
                "(110, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Effect of Air Water and H2', '15', '"+ getVideoUri("video") +"')," +
                "(111, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Halides Nitrates Sulphates Carbonates and Bicarbonates', '20', '"+ getVideoUri("video") +"')," +
                "(112, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Effect of Liquid NH3 and Diagonal Relationship', '12', '"+ getVideoUri("video") +"')," +
                "(113, 'Class 11', 'Chemistry', '10.THE S-BLOCK ELEMENTS', '10.3 Some of the Chemical Reactions of Gp-2 Elements', 'Effect of Carbon and Oxides and Sulphates of Ca and Mg', '20', '"+ getVideoUri("video") +"')," +
                "(114, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'Introduction Inert pair Effect', '15', '"+ getVideoUri("video") +"')," +
                "(115, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'General Chemical Properties', '20', '"+ getVideoUri("video") +"')," +
                "(116, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'Some more Chemical Properties', '20', '"+ getVideoUri("video") +"')," +
                "(117, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.1 Group-13 Elements', 'Extraction of Boron from its Ores', '20', '"+ getVideoUri("video") +"')," +
                "(118, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Electrolytic Method for Extraction of B', '20', '"+ getVideoUri("video") +"')," +
                "(119, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Diborane and Borazole', '25', '"+ getVideoUri("video") +"')," +
                "(120, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Boron Nitride and Some of the Compounds of Aluminium', '15', '"+ getVideoUri("video") +"')," +
                "(121, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Group-14 Elements', '20', '"+ getVideoUri("video") +"')," +
                "(122, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Compounds of Carbon', '12', '"+ getVideoUri("video") +"')," +
                "(123, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Halides Oxides of Group-14 Elements Allotropes of Carbon', '10', '"+ getVideoUri("video") +"')," +
                "(124, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Carbides and Carbon Monoxide', '15', '"+ getVideoUri("video") +"')," +
                "(125, 'Class 11', 'Chemistry', '11. THE P-BLOCK ELEMENTS', '11.2 Extraction of B and its Compounds Physical Properties of Gp-14 Elements', 'Silicon', '20', '"+ getVideoUri("video") +"')," +
                "(126, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Bonding and Hybridisation of Organic Compounds', '15', '"+ getVideoUri("video") +"')," +
                "(127, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Nomenclature of Hydrocarbons', '15', '"+ getVideoUri("video") +"')," +
                "(128, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Nomenclature of Monofunctional Compounds ', '20', '"+ getVideoUri("video") +"')," +
                "(129, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.1 IUPAC Nomenclature of Organic Compounds', 'Nomenclature of Polyfunctional Compounds', '15', '"+ getVideoUri("video") +"')," +
                "(130, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.2 Isomerism in Organic Compounds', 'Structural isomerism', '20', '"+ getVideoUri("video") +"')," +
                "(131, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.2 Isomerism in Organic Compounds', 'Stereoisomerism', '22', '"+ getVideoUri("video") +"')," +
                "(132, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.3 Electronic Effects', 'Fission of Bonds', '21', '"+ getVideoUri("video") +"')," +
                "(133, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.3 Electronic Effects', 'Electronic Effects', '10', '"+ getVideoUri("video") +"')," +
                "(134, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.4 Aromaticity', 'Resonance Effect', '8', '"+ getVideoUri("video") +"')," +
                "(135, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.4 Aromaticity', 'Hyperconjugation', '15', '"+ getVideoUri("video") +"')," +
                "(136, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.4 Aromaticity', 'Aromaticity', '20', '"+ getVideoUri("video") +"')," +
                "(137, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.5 Intermediate Compounds', 'Tautomerism', '20', '"+ getVideoUri("video") +"')," +
                "(138, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.5 Intermediate Compounds', 'Intermediate Compounds', '15', '"+ getVideoUri("video") +"')," +
                "(139, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Addition Reactions', '20', '"+ getVideoUri("video") +"')," +
                "(140, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Substitution Reactions', '15', '"+ getVideoUri("video") +"')," +
                "(141, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Elimination Reactions', '14', '"+ getVideoUri("video") +"')," +
                "(142, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.6 Types of Organic Reactions', 'Rearrangements', '10', '"+ getVideoUri("video") +"')," +
                "(143, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.7 Purification of Organic Compounds', 'Distillation', '8', '"+ getVideoUri("video") +"')," +
                "(144, 'Class 11', 'Chemistry', '12. ORGANIC CHEMISTRY-BASIC CONCEPTS AND TECHNIQUES', '12.7 Purification of Organic Compounds', 'Chromatography', '7', '"+ getVideoUri("video") +"')," +
                "(145, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.1 Alkanes', 'Preparation of Alkanes', '15', '"+ getVideoUri("video") +"')," +
                "(146, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.1 Alkanes', 'Physical Properties of Alkanes', '20', '"+ getVideoUri("video") +"')," +
                "(147, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.1 Alkanes', 'Chemical Properties of Alkanes', '12', '"+ getVideoUri("video") +"')," +
                "(148, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.2 Alkenes', 'Preparation of Alkenes', '12', '"+ getVideoUri("video") +"')," +
                "(149, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.2 Alkenes', 'Physical Properties of Alkenes', '20', '"+ getVideoUri("video") +"')," +
                "(150, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.2 Alkenes', 'Chemical Properties of Alkenes', '20', '"+ getVideoUri("video") +"')," +
                "(151, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.3 Alkenes and Alkynes ', 'Chemical Properties of Alkenes', '20', '"+ getVideoUri("video") +"')," +
                "(152, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.3 Alkenes and Alkynes ', 'Structure of Alkynes', '20', '"+ getVideoUri("video") +"')," +
                "(153, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.3 Alkenes and Alkynes ', 'Preparation of Alkynes', '20', '"+ getVideoUri("video") +"')," +
                "(154, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Aromaticity', '12', '"+ getVideoUri("video") +"')," +
                "(155, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Electrophillic Aromatic Substitution', '15', '"+ getVideoUri("video") +"')," +
                "(156, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Orientation in Disubstituted Rings', '15', '"+ getVideoUri("video") +"')," +
                "(157, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Preparation of Benzene', '16', '"+ getVideoUri("video") +"')," +
                "(158, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Chemical Properties of Benzene', '14', '"+ getVideoUri("video") +"')," +
                "(159, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Chemical Properties of Toulene', '12', '"+ getVideoUri("video") +"')," +
                "(160, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.4 Aromatic Hydrocarbons', 'Ozonolysis of Aromatic Hydrocarbons', '10', '"+ getVideoUri("video") +"')," +
                "(161, 'Class 11', 'Chemistry', '13. HYDROCARBONS', '13.5 Alkynes', 'Chemical Properties of Alkynes', '10', '"+ getVideoUri("video") +"')," +
                "(162, 'Class 11', 'Chemistry', '14. ENVIRONMENTAL CHEMISTRY', '14.1 Environmental Chemistry', 'Air Pollution Introduction and Pollutant', '15', '"+ getVideoUri("video") +"')," +
                "(163, 'Class 11', 'Chemistry', '14. ENVIRONMENTAL CHEMISTRY', '14.1 Environmental Chemistry', 'Oxides of C N Acid Rain Particulate Matter', '20', '"+ getVideoUri("video") +"')," +
                "(164, 'Class 11', 'Chemistry', '14. ENVIRONMENTAL CHEMISTRY', '14.1 Environmental Chemistry', 'Water Pollution Soil Pollution', '15', '"+ getVideoUri("video") +"')," +
                "(165, 'Class 11', 'Physics', '1. PHYSICAL WORLD', '1.1 Physical World', 'Scope and excitement of Physics', '10', '"+ getVideoUri("video") +"')," +
                "(166, 'Class 11', 'Physics', '1. PHYSICAL WORLD', '1.1 Physical World', 'Fundamental forces in nature', '15', '"+ getVideoUri("video") +"')," +
                "(167, 'Class 11', 'Physics', '1. PHYSICAL WORLD', '1.1 Physical World', 'Unification of forces', '12', '"+ getVideoUri("video") +"')," +
                "(168, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.1 Units and Dimensions', 'Dimensions : Definition-MKS CGS FPS SYSTEM', '10', '"+ getVideoUri("video") +"')," +
                "(169, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.1 Units and Dimensions', 'Application of Dimensions', '20', '"+ getVideoUri("video") +"')," +
                "(170, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.2 Measurement', 'Accuracy and Precision Significant Figure', '20', '"+ getVideoUri("video") +"')," +
                "(171, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.2 Measurement', 'Vernier Calliper', '12', '"+ getVideoUri("video") +"')," +
                "(172, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.2 Measurement', 'Screw Gauge', '15', '"+ getVideoUri("video") +"')," +
                "(173, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.2 Measurement', 'Rounding Off', '15', '"+ getVideoUri("video") +"')," +
                "(174, 'Class 11', 'Physics', '2. UNITS AND MEASUREMENTS', '2.2 Measurement', 'Error', '15', '"+ getVideoUri("video") +"')," +
                "(175, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.1 Introductory Concepts of Kinematics', 'Concept of distance Displacement and Velocity', '15', '"+ getVideoUri("video") +"')," +
                "(176, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.1 Introductory Concepts of Kinematics', 'Average Speed and Average Velocity in 1 and 2 Dimensions', '10', '"+ getVideoUri("video") +"')," +
                "(177, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.1 Introductory Concepts of Kinematics', 'Average and Instantaneous Acceleration', '10', '"+ getVideoUri("video") +"')," +
                "(178, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.2 Motion in 1-Dimension', 'General Equation of Motion in 1-Dimension', '15', '"+ getVideoUri("video") +"')," +
                "(179, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.2 Motion in 1-Dimension', 'Uniformly Accelerated Motion in one Dimension', '15', '"+ getVideoUri("video") +"')," +
                "(180, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.2 Motion in 1-Dimension', 'Examples on Uniformly Accelerated Motion', '20', '"+ getVideoUri("video") +"')," +
                "(181, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.3 Motion under Gravity', 'Upward and Downward Projection', '20', '"+ getVideoUri("video") +"')," +
                "(182, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.3 Motion under Gravity', 'Displacement of a Freely Falling Body in a Time Interval', '20', '"+ getVideoUri("video") +"')," +
                "(183, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.3 Motion under Gravity', 'Additional Examples', '15', '"+ getVideoUri("video") +"')," +
                "(184, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.4 Graphical Representation of Motion', 'Position-Time Graph', '15', '"+ getVideoUri("video") +"')," +
                "(185, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.4 Graphical Representation of Motion', 'Velocity-Time Graph and Acceleration-Time Graph', '15', '"+ getVideoUri("video") +"')," +
                "(186, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.4 Graphical Representation of Motion', 'Special Graphs like v-x and a-x Graphs', '20', '"+ getVideoUri("video") +"')," +
                "(187, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.4 Graphical Representation of Motion', 'Relative Motion in 1-Dimension', '20', '"+ getVideoUri("video") +"')," +
                "(188, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.5 Calculus Application', 'Application of Derivative', '20', '"+ getVideoUri("video") +"')," +
                "(189, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.5 Calculus Application', 'Integration-indefinite Integaral', '20', '"+ getVideoUri("video") +"')," +
                "(190, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.5 Calculus Application', 'Application of Integration', '20', '"+ getVideoUri("video") +"')," +
                "(191, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.5 Calculus Application', 'Functions', '20', '"+ getVideoUri("video") +"')," +
                "(192, 'Class 11', 'Physics', '3. MOTION IN A STRAIGHT LINE', '3.5 Calculus Application', 'Differentiation', '20', '"+ getVideoUri("video") +"')," +
                "(193, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.1 Vector', 'Vector and its type Definition of Vector Angle between Two Vector', '10', '"+ getVideoUri("video") +"')," +
                "(194, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.1 Vector', 'Vector Addition: Triangle Law of Addition Polygon Law of Addition', '10', '"+ getVideoUri("video") +"')," +
                "(195, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.1 Vector', 'Parallelogram law of Addition Vector Subtraction', '10', '"+ getVideoUri("video") +"')," +
                "(196, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.1 Vector', 'Zero Resultant Unit Vector Position Vector Directional Cosine', '10', '"+ getVideoUri("video") +"')," +
                "(197, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.1 Vector', 'Vector Resolution Displacement Properties of Vector Addition', '10', '"+ getVideoUri("video") +"')," +
                "(198, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.2 Motion in 2-Dimensions', 'General Equations in 2-D Motion', '10', '"+ getVideoUri("video") +"')," +
                "(199, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.2 Motion in 2-Dimensions', 'Projectile Motion(Ground to Ground)', '10', '"+ getVideoUri("video") +"')," +
                "(200, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.2 Motion in 2-Dimensions', 'Projection from Elevated Platform', '10', '"+ getVideoUri("video") +"')," +
                "(201, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.3 Projectile on an Incline', 'Projection from Incline in Upward and Downward Direction', '10', '"+ getVideoUri("video") +"')," +
                "(202, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.4 Relative Motion', 'Relative Motion in 2-Dimension', '10', '"+ getVideoUri("video") +"')," +
                "(203, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.4 Relative Motion', 'Relative Motion in Projectliles', '10', '"+ getVideoUri("video") +"')," +
                "(204, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.4 Relative Motion', 'Velocity of Approch and Condition for Collision', '10', '"+ getVideoUri("video") +"')," +
                "(205, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.4 Relative Motion', 'Shortest Distance or Closest Approach', '10', '"+ getVideoUri("video") +"')," +
                "(206, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.5 Kinematics of Circular Motion', 'Angular Variables in Circular Motion', '10', '"+ getVideoUri("video") +"')," +
                "(207, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.5 Kinematics of Circular Motion', 'Acceleration in Circular Motion', '10', '"+ getVideoUri("video") +"')," +
                "(208, 'Class 11', 'Physics', '4. MOTION IN A PLANE', '4.5 Kinematics of Circular Motion', 'Radius of Curvature', '10', '"+ getVideoUri("video") +"')," +
                "(209, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.1 Newtonss Law of Motion', 'Forces and Newtons First law', '15', '"+ getVideoUri("video") +"')," +
                "(210, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.1 Newtonss Law of Motion', 'Momentum and Newtons Second Law', '15', '"+ getVideoUri("video") +"')," +
                "(211, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.1 Newtonss Law of Motion', 'Impulse Newtons Third Law', '20', '"+ getVideoUri("video") +"')," +
                "(212, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.2 Conservation of Linear Momentum and Variable Mass System', 'Conservation of Linear Momentum', '20', '"+ getVideoUri("video") +"')," +
                "(213, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.2 Conservation of Linear Momentum and Variable Mass System', 'Variable Mass System', '20', '"+ getVideoUri("video") +"')," +
                "(214, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.3 Application of Laws of Motion', 'Common Forces', '20', '"+ getVideoUri("video") +"')," +
                "(215, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.3 Application of Laws of Motion', 'Equilibrium of Forces', '15', '"+ getVideoUri("video") +"')," +
                "(216, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.3 Application of Laws of Motion', 'Motion of Connected Bodies', '15', '"+ getVideoUri("video") +"')," +
                "(217, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.3 Application of Laws of Motion', 'Concept of Net Pulling Force or Driving Force', '20', '"+ getVideoUri("video") +"')," +
                "(218, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.4 Constraint Equations', 'String Constraint', '20', '"+ getVideoUri("video") +"')," +
                "(219, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.4 Constraint Equations', 'Pulley Constraint', '15', '"+ getVideoUri("video") +"')," +
                "(220, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.4 Constraint Equations', 'Wedge Constraint', '15', '"+ getVideoUri("video") +"')," +
                "(221, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.5 Friction', 'Static and Kinetic Friction', '20', '"+ getVideoUri("video") +"')," +
                "(222, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.5 Friction', 'Angle of Repose Angle of Friction Pusing and Pulling a Body', '20', '"+ getVideoUri("video") +"')," +
                "(223, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.5 Friction', 'Block on Block Systems', '12', '"+ getVideoUri("video") +"')," +
                "(224, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.6 Pseudo forces', 'Non-inertial Frames and Pseudo forces', '20', '"+ getVideoUri("video") +"')," +
                "(225, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.7 Dynamics of Circular Motion', 'Centripetal and Tangential Forces', '15', '"+ getVideoUri("video") +"')," +
                "(226, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.7 Dynamics of Circular Motion', 'Centrifugal Force', '15', '"+ getVideoUri("video") +"')," +
                "(227, 'Class 11', 'Physics', '5. LAW OF MOTION', '5.7 Dynamics of Circular Motion', 'Bending of Cyclist and Banking of Roads', '15', '"+ getVideoUri("video") +"')," +
                "(228, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.1 Work and Work-Eneregy Theorem', 'Dot Product', '10', '"+ getVideoUri("video") +"')," +
                "(229, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.1 Work and Work-Eneregy Theorem', 'Concept of Kinetic Energy and Work', '15', '"+ getVideoUri("video") +"')," +
                "(230, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.1 Work and Work-Eneregy Theorem', 'Calculation of Work', '10', '"+ getVideoUri("video") +"')," +
                "(231, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.1 Work and Work-Eneregy Theorem', 'Work-Energy Theorem', '10', '"+ getVideoUri("video") +"')," +
                "(232, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.2 Work done by Commen Forces and Power', 'Some important Points Related to Work', '10', '"+ getVideoUri("video") +"')," +
                "(233, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.2 Work done by Commen Forces and Power', 'Work done by Friction Spring Force Gravity and Pseudo forces', '10', '"+ getVideoUri("video") +"')," +
                "(234, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.2 Work done by Commen Forces and Power', 'Power', '10', '"+ getVideoUri("video") +"')," +
                "(235, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.3 Conservation of Energy', 'Conservative Forces and Potential Energy', '12', '"+ getVideoUri("video") +"')," +
                "(236, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.3 Conservation of Energy', 'Gravitational Potential Energy and Spring Potential Energy', '15', '"+ getVideoUri("video") +"')," +
                "(237, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.3 Conservation of Energy', 'Conservation of Energy', '15', '"+ getVideoUri("video") +"')," +
                "(238, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.4 Motion in Vertical Circle Stability of Equilibrium', 'Motion in Verticle Circle', '10', '"+ getVideoUri("video") +"')," +
                "(239, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.4 Motion in Vertical Circle Stability of Equilibrium', 'Relation between F & U and Equilibrium', '10', '"+ getVideoUri("video") +"')," +
                "(240, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.5 Collision', 'Collision and its Type Coefficient of Restitution', '15', '"+ getVideoUri("video") +"')," +
                "(241, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.5 Collision', 'Head on Collision', '15', '"+ getVideoUri("video") +"')," +
                "(242, 'Class 11', 'Physics', '6. WORK ENERGY AND POWER', '6.5 Collision', 'Oblique Collision Impulse Momentum Theorm', '15', '"+ getVideoUri("video") +"')," +
                "(243, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.1 Vector', 'Cross Product Scaler Triple Product', '10', '"+ getVideoUri("video") +"')," +
                "(244, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.1 Vector', 'Geometrical Signifance of Products', '15', '"+ getVideoUri("video") +"')," +
                "(245, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.2 Centre of Mass and its Motion', 'Definition of Centre of Mass', '10', '"+ getVideoUri("video") +"')," +
                "(246, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.2 Centre of Mass and its Motion', 'Velocity of Centre of Mass', '10', '"+ getVideoUri("video") +"')," +
                "(247, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.2 Centre of Mass and its Motion', 'Motion of Centre of Mass under Internal Force', '10', '"+ getVideoUri("video") +"')," +
                "(248, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.3 Frame and Centre of Motion for Continous Bodies', 'C Frame', '15', '"+ getVideoUri("video") +"')," +
                "(249, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.3 Frame and Centre of Motion for Continous Bodies', 'Centre of Mass for Continuous Bodies', '15', '"+ getVideoUri("video") +"')," +
                "(250, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.4 Rigid Body and Torque', 'Translatory and Rotatory Motion', '15', '"+ getVideoUri("video") +"')," +
                "(251, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.4 Rigid Body and Torque', 'Kinematics and Constraint of Rigid Body', '20', '"+ getVideoUri("video") +"')," +
                "(252, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.4 Rigid Body and Torque', 'Constraints and Torque', '20', '"+ getVideoUri("video") +"')," +
                "(253, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.5 Equilibrium and Moment of Inertia', 'Equilibrium and Moment of Inertia', '22', '"+ getVideoUri("video") +"')," +
                "(254, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.5 Equilibrium and Moment of Inertia', 'Equilibrium and Normal Reaction Shift', '15', '"+ getVideoUri("video") +"')," +
                "(255, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.5 Equilibrium and Moment of Inertia', 'Toppling and Moment of Inertia', '18', '"+ getVideoUri("video") +"')," +
                "(256, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.6 Moment of Inertia for Continuous Bodies', 'Moment of Inertia', '10', '"+ getVideoUri("video") +"')," +
                "(257, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.6 Moment of Inertia for Continuous Bodies', 'Moment of Inertia for Continuous Bodies', '10', '"+ getVideoUri("video") +"')," +
                "(258, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.6 Moment of Inertia for Continuous Bodies', 'Moment of Inertia for Objects having Cavity', '10', '"+ getVideoUri("video") +"')," +
                "(259, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.7 Rotation about Fixed Axis and Rolling', 'Newtons Law for Rotation', '15', '"+ getVideoUri("video") +"')," +
                "(260, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.7 Rotation about Fixed Axis and Rolling', 'Hinged Body', '15', '"+ getVideoUri("video") +"')," +
                "(261, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.7 Rotation about Fixed Axis and Rolling', 'Rolling Motion', '15', '"+ getVideoUri("video") +"')," +
                "(262, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.8 Dynamics of Rolling', 'Accelerated Pure Rolling', '20', '"+ getVideoUri("video") +"')," +
                "(263, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.8 Dynamics of Rolling', 'Friction in Rolling', '20', '"+ getVideoUri("video") +"')," +
                "(264, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.8 Dynamics of Rolling', 'Rolling on Inclined Plane and Impure Rolling', '20', '"+ getVideoUri("video") +"')," +
                "(265, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.9 Work and Energy', 'Kinetic Energy', '18', '"+ getVideoUri("video") +"')," +
                "(266, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.9 Work and Energy', 'Work Done', '17', '"+ getVideoUri("video") +"')," +
                "(267, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.9 Work and Energy', 'Energy in Rolling', '20', '"+ getVideoUri("video") +"')," +
                "(268, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.10 Instantaneous Axis of Rotation and Angular Momentum', 'Instantaneous Axis of Rotation', '20', '"+ getVideoUri("video") +"')," +
                "(269, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.10 Instantaneous Axis of Rotation and Angular Momentum', 'Angular Momentum', '15', '"+ getVideoUri("video") +"')," +
                "(270, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.11 Angular Momentum and its Conservation', 'Angular Momentum for Continuous Bodies', '20', '"+ getVideoUri("video") +"')," +
                "(271, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.11 Angular Momentum and its Conservation', 'Conservation of Angular Momentum', '20', '"+ getVideoUri("video") +"')," +
                "(272, 'Class 11', 'Physics', '7. SYSTEM OF PARTICLES ROTATIONAL MOTION', '7.11 Angular Momentum and its Conservation', 'Collisions and Angular Impulse', '20', '"+ getVideoUri("video") +"')," +
                "(273, 'Class 11', 'Physics', '8. GRAVITATION', '8.1 Gravititational Forces and Field Intensity', 'Gravitational Force and Newtons Law', '20', '"+ getVideoUri("video") +"')," +
                "(274, 'Class 11', 'Physics', '8. GRAVITATION', '8.1 Gravititational Forces and Field Intensity', 'Calculation of Gravitational Forces', '15', '"+ getVideoUri("video") +"')," +
                "(275, 'Class 11', 'Physics', '8. GRAVITATION', '8.1 Gravititational Forces and Field Intensity', 'Gravitational Field Intensity', '15', '"+ getVideoUri("video") +"')," +
                "(276, 'Class 11', 'Physics', '8. GRAVITATION', '8.2 Field Intensity due to Spherical Bodies and Acceleration due to Gravity', 'Gravitational Field Intensity due to Spherical Bodies', '20', '"+ getVideoUri("video") +"')," +
                "(277, 'Class 11', 'Physics', '8. GRAVITATION', '8.2 Field Intensity due to Spherical Bodies and Acceleration due to Gravity', 'Calculation of Field Intensity', '15', '"+ getVideoUri("video") +"')," +
                "(278, 'Class 11', 'Physics', '8. GRAVITATION', '8.2 Field Intensity due to Spherical Bodies and Acceleration due to Gravity', 'Acceleration due to Gravity', '15', '"+ getVideoUri("video") +"')," +
                "(279, 'Class 11', 'Physics', '8. GRAVITATION', '8.3 Gravitational Potential Energy and Gravitational Potential', 'Gravitational Potential Energy', '20', '"+ getVideoUri("video") +"')," +
                "(280, 'Class 11', 'Physics', '8. GRAVITATION', '8.3 Gravitational Potential Energy and Gravitational Potential', 'Gravitational Potential', '10', '"+ getVideoUri("video") +"')," +
                "(281, 'Class 11', 'Physics', '8. GRAVITATION', '8.3 Gravitational Potential Energy and Gravitational Potential', 'Potential due to Spherical Bodies', '10', '"+ getVideoUri("video") +"')," +
                "(282, 'Class 11', 'Physics', '8. GRAVITATION', '8.3 Gravitational Potential Energy and Gravitational Potential', 'Conservation of Energy in Motion under Gravitational Forces', '10', '"+ getVideoUri("video") +"')," +
                "(283, 'Class 11', 'Physics', '8. GRAVITATION', '8.3 Gravitational Potential Energy and Gravitational Potential', 'Binding Energy and Escape Velocity', '15', '"+ getVideoUri("video") +"')," +
                "(284, 'Class 11', 'Physics', '8. GRAVITATION', '8.4 Motion of Satellites and Planetary Motion', 'Orbital Velocity of a satellite', '10', '"+ getVideoUri("video") +"')," +
                "(285, 'Class 11', 'Physics', '8. GRAVITATION', '8.4 Motion of Satellites and Planetary Motion', 'Energy of a Satellite', '20', '"+ getVideoUri("video") +"')," +
                "(286, 'Class 11', 'Physics', '8. GRAVITATION', '8.4 Motion of Satellites and Planetary Motion', 'Keplers Laws', '15', '"+ getVideoUri("video") +"')," +
                "(287, 'Class 11', 'Physics', '9. MECHANICAL PRPERTIES OF SOLIDS', '9.1 Elasticity', 'Basic Concepts of Elasticity', '10', '"+ getVideoUri("video") +"')," +
                "(288, 'Class 11', 'Physics', '9. MECHANICAL PRPERTIES OF SOLIDS', '9.1 Elasticity', 'Hookes Law', '20', '"+ getVideoUri("video") +"')," +
                "(289, 'Class 11', 'Physics', '9. MECHANICAL PRPERTIES OF SOLIDS', '9.1 Elasticity', 'Examples based on Longitudinal Deformation', '15', '"+ getVideoUri("video") +"')," +
                "(290, 'Class 11', 'Physics', '9. MECHANICAL PRPERTIES OF SOLIDS', '9.1 Elasticity', 'Volmetric and Shear Strain', '20', '"+ getVideoUri("video") +"')," +
                "(291, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.1 Fluids and Hydrostatic Pressure', 'Basic Properties of Fluids', '10', '"+ getVideoUri("video") +"')," +
                "(292, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.1 Fluids and Hydrostatic Pressure', 'Pressure inside a Liquid and Hydrostatic Paradox', '20', '"+ getVideoUri("video") +"')," +
                "(293, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.1 Fluids and Hydrostatic Pressure', 'Applications of Hydrostatic Pressure', '15', '"+ getVideoUri("video") +"')," +
                "(294, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.2 Pressure Inside Accelerated Liquids and Atmospheric Pressure', 'Atmospheric Pressure', '10', '"+ getVideoUri("video") +"')," +
                "(295, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.2 Pressure Inside Accelerated Liquids and Atmospheric Pressure', 'Liquids in Accelerated Vessels', '20', '"+ getVideoUri("video") +"')," +
                "(296, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.2 Pressure Inside Accelerated Liquids and Atmospheric Pressure', 'Rotating Liquids', '15', '"+ getVideoUri("video") +"')," +
                "(297, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.3 Archimedes Principle', 'Buoyancy and Archimedes Principle', '12', '"+ getVideoUri("video") +"')," +
                "(298, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.3 Archimedes Principle', 'Apparent Weight', '12', '"+ getVideoUri("video") +"')," +
                "(299, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.3 Archimedes Principle', 'Examples based on Archimedes Principle', '15', '"+ getVideoUri("video") +"')," +
                "(300, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.4 Surface Tension', 'Surface Tension Force', '12', '"+ getVideoUri("video") +"')," +
                "(301, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.4 Surface Tension', 'Surface Energy', '15', '"+ getVideoUri("video") +"')," +
                "(302, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.4 Surface Tension', 'Excess Pressure', '20', '"+ getVideoUri("video") +"')," +
                "(303, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.4 Surface Tension', 'Angle of Contact and Capillarity', '20', '"+ getVideoUri("video") +"')," +
                "(304, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.4 Surface Tension', 'Viscous Forces', '15', '"+ getVideoUri("video") +"')," +
                "(305, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.4 Surface Tension', 'Pipe Flow of Viscous Liquids', '15', '"+ getVideoUri("video") +"')," +
                "(306, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.5 Fluid Dynamics', 'Ideal Fluid Flow and Continuity Equation', '20', '"+ getVideoUri("video") +"')," +
                "(307, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.5 Fluid Dynamics', 'Bernoullis Equation', '20', '"+ getVideoUri("video") +"')," +
                "(308, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.5 Fluid Dynamics', 'Efflux of a Liquid', '15', '"+ getVideoUri("video") +"')," +
                "(309, 'Class 11', 'Physics', '10. MECHANICAL PROPERTIES OF FLUIDS', '10.5 Fluid Dynamics', 'Applications of Bernoullis Equation', '15', '"+ getVideoUri("video") +"')," +
                "(310, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.1 Thermometry and Thermal Expansion', 'Heat and Temperature', '15', '"+ getVideoUri("video") +"')," +
                "(311, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.1 Thermometry and Thermal Expansion', 'Thermometry', '20', '"+ getVideoUri("video") +"')," +
                "(312, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.1 Thermometry and Thermal Expansion', 'Thermal Expansion', '12', '"+ getVideoUri("video") +"')," +
                "(313, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.1 Thermometry and Thermal Expansion', 'Heat ', '15', '"+ getVideoUri("video") +"')," +
                "(314, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.2 Calorimetry and Modes of Heat Transfer(Conduction)', 'Calorimetry', '10', '"+ getVideoUri("video") +"')," +
                "(315, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.2 Calorimetry and Modes of Heat Transfer(Conduction)', 'Modes of Heat Transfer', '10', '"+ getVideoUri("video") +"')," +
                "(316, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.2 Calorimetry and Modes of Heat Transfer(Conduction)', 'Series and Parallel Combination of Roads', '20', '"+ getVideoUri("video") +"')," +
                "(317, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.2 Calorimetry and Modes of Heat Transfer(Conduction)', 'Conduction through Non-Uniform Cross-section', '15', '"+ getVideoUri("video") +"')," +
                "(318, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.2 Calorimetry and Modes of Heat Transfer(Conduction)', 'Calculation of Time in Conduction', '20', '"+ getVideoUri("video") +"')," +
                "(319, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.3 Modes of Heat Transfer(Radiation)', 'Introduction to Radiation', '20', '"+ getVideoUri("video") +"')," +
                "(320, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.3 Modes of Heat Transfer(Radiation)', 'Kirchoffs Law and Weins Law', '15', '"+ getVideoUri("video") +"')," +
                "(321, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.3 Modes of Heat Transfer(Radiation)', 'Newtonss Law of Cooling', '15', '"+ getVideoUri("video") +"')," +
                "(322, 'Class 11', 'Physics', '11. THERMAL PROPERTIES OF MATTER', '11.3 Modes of Heat Transfer(Radiation)', 'Solor Constant', '20', '"+ getVideoUri("video") +"')," +
                "(323, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.1 Thermodynamic Processes and Energy Terms in Thermodynamics', 'Equation of State and Thermodynamic Processes', '15', '"+ getVideoUri("video") +"')," +
                "(324, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.1 Thermodynamic Processes and Energy Terms in Thermodynamics', 'Graphical Representation of Thermodynamic Processes', '20', '"+ getVideoUri("video") +"')," +
                "(325, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.1 Thermodynamic Processes and Energy Terms in Thermodynamics', 'Energy Terms in Thermodynamic Processes', '20', '"+ getVideoUri("video") +"')," +
                "(326, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.2 First Law of Thermodynamics', 'First Law of Thermodynamics', '20', '"+ getVideoUri("video") +"')," +
                "(327, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.2 First Law of Thermodynamics', 'Application of First Law of Thermodynamics', '20', '"+ getVideoUri("video") +"')," +
                "(328, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.2 First Law of Thermodynamics', 'Examples Based on First Law of Thermodynamics', '20', '"+ getVideoUri("video") +"')," +
                "(329, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.3 Specific Heats of a Gas and Second Law of Thermodynamics', 'Specific Heats of a Gas', '15', '"+ getVideoUri("video") +"')," +
                "(330, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.3 Specific Heats of a Gas and Second Law of Thermodynamics', 'Efficiency of a Cyclic Process', '15', '"+ getVideoUri("video") +"')," +
                "(331, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.3 Specific Heats of a Gas and Second Law of Thermodynamics', 'Second Law of Thermodynamics', '15', '"+ getVideoUri("video") +"')," +
                "(332, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.3 Specific Heats of a Gas and Second Law of Thermodynamics', 'Carnot Engine and Refrigerator', '15', '"+ getVideoUri("video") +"')," +
                "(333, 'Class 11', 'Physics', '12. THERMODYNAMICS', '12.3 Specific Heats of a Gas and Second Law of Thermodynamics', 'Examples based on Second Law of Thermodynamics', '15', '"+ getVideoUri("video") +"')," +
                "(334, 'Class 11', 'Physics', '13. KINETIC THEORY', '13.1 Kinetic Model of Ideal Gases', 'Kinetic Model of Ideal Gases', '15', '"+ getVideoUri("video") +"')," +
                "(335, 'Class 11', 'Physics', '13. KINETIC THEORY', '13.1 Kinetic Model of Ideal Gases', 'Kinetic Interpretation of Temperature', '10', '"+ getVideoUri("video") +"')," +
                "(336, 'Class 11', 'Physics', '13. KINETIC THEORY', '13.1 Kinetic Model of Ideal Gases', 'Internal Energy of an Ideal Gas', '10', '"+ getVideoUri("video") +"')," +
                "(337, 'Class 11', 'Physics', '13. KINETIC THEORY', '13.1 Kinetic Model of Ideal Gases', 'Maxwells Distribution of Speeds', '12', '"+ getVideoUri("video") +"')," +
                "(338, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.1 Equation of SHM', 'Introduction', '15', '"+ getVideoUri("video") +"')," +
                "(339, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.1 Equation of SHM', 'Mathematical Meaning of SHM', '10', '"+ getVideoUri("video") +"')," +
                "(340, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.1 Equation of SHM', 'Differentiation Equation of SHM', '12', '"+ getVideoUri("video") +"')," +
                "(341, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.2 SHM Kinematics', 'Energy in SHM', '12', '"+ getVideoUri("video") +"')," +
                "(342, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.2 SHM Kinematics', 'Composition of SHM & Time Period', '12', '"+ getVideoUri("video") +"')," +
                "(343, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.2 SHM Kinematics', 'Time Period', '15', '"+ getVideoUri("video") +"')," +
                "(344, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.3 Angular SHM and Special Cases', 'Angular SHM', '20', '"+ getVideoUri("video") +"')," +
                "(345, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.3 Angular SHM and Special Cases', 'Numericals of Angular SHM', '20', '"+ getVideoUri("video") +"')," +
                "(346, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.3 Angular SHM and Special Cases', 'Simple Pendulum', '20', '"+ getVideoUri("video") +"');";

        db.execSQL(sql);

        String sql2 = "INSERT INTO `topic` (`topic_id`, `class`, `subject`, `chapter`, `topic_name`, `sub_topic_name`, `topic_price`, `topic_video_url`) VALUES" +
                "(347, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.3 Angular SHM and Special Cases', 'Energy Method of Calculating Time Period', '12', '"+ getVideoUri("video") +"')," +
                "(348, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.4 Dampled Oscillation', 'Introduction to Dampled Oscillatory Motion', '15', '"+ getVideoUri("video") +"')," +
                "(349, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.4 Dampled Oscillation', 'General Damped Motion', '12', '"+ getVideoUri("video") +"')," +
                "(350, 'Class 11', 'Physics', '14. OSCILLATIONS', '14.4 Dampled Oscillation', 'Resonance', '12', '"+ getVideoUri("video") +"')," +
                "(351, 'Class 11', 'Physics', '15. WAVES', '15.1 Wave Equation', 'Introduction', '15', '"+ getVideoUri("video") +"')," +
                "(352, 'Class 11', 'Physics', '15. WAVES', '15.1 Wave Equation', 'Wave in String y=f(x t)', '10', '"+ getVideoUri("video") +"')," +
                "(353, 'Class 11', 'Physics', '15. WAVES', '15.1 Wave Equation', 'Wave Equation', '10', '"+ getVideoUri("video") +"')," +
                "(354, 'Class 11', 'Physics', '15. WAVES', '15.2 Wave in Strings', 'Wave and Particle Velocity', '12', '"+ getVideoUri("video") +"')," +
                "(355, 'Class 11', 'Physics', '15. WAVES', '15.2 Wave in Strings', 'Pulse', '12', '"+ getVideoUri("video") +"')," +
                "(356, 'Class 11', 'Physics', '15. WAVES', '15.2 Wave in Strings', 'Waves in String', '12', '"+ getVideoUri("video") +"')," +
                "(357, 'Class 11', 'Physics', '15. WAVES', '15.3 Energy of Waves and Superposition', 'Energy of Waves', '15', '"+ getVideoUri("video") +"')," +
                "(358, 'Class 11', 'Physics', '15. WAVES', '15.3 Energy of Waves and Superposition', 'Waves at Boundary', '20', '"+ getVideoUri("video") +"')," +
                "(359, 'Class 11', 'Physics', '15. WAVES', '15.4 Longitudinal Waves', 'Introduction', '15', '"+ getVideoUri("video") +"')," +
                "(360, 'Class 11', 'Physics', '15. WAVES', '15.4 Longitudinal Waves', 'Propagation of Longitudinal Wave', '20', '"+ getVideoUri("video") +"')," +
                "(361, 'Class 11', 'Physics', '15. WAVES', '15.4 Longitudinal Waves', 'Sound as Pressure Wave', '20', '"+ getVideoUri("video") +"')," +
                "(362, 'Class 11', 'Physics', '15. WAVES', '15.4 Longitudinal Waves', 'Wave Energy and Intensity', '15', '"+ getVideoUri("video") +"')," +
                "(363, 'Class 11', 'Physics', '15. WAVES', '15.5 Transverse Standing Wave', 'Wave Superposition', '20', '"+ getVideoUri("video") +"')," +
                "(364, 'Class 11', 'Physics', '15. WAVES', '15.5 Transverse Standing Wave', 'Standing Wave', '15', '"+ getVideoUri("video") +"')," +
                "(365, 'Class 11', 'Physics', '15. WAVES', '15.5 Transverse Standing Wave', 'Standing Waves in String', '18', '"+ getVideoUri("video") +"')," +
                "(366, 'Class 11', 'Physics', '15. WAVES', '15.6 Longitudinal Standing Wave and Beats', 'Longitudinal Standing Waves', '20', '"+ getVideoUri("video") +"')," +
                "(367, 'Class 11', 'Physics', '15. WAVES', '15.6 Longitudinal Standing Wave and Beats', 'Resonance and Beats', '12', '"+ getVideoUri("video") +"')," +
                "(368, 'Class 11', 'Physics', '15. WAVES', '15.7 Interference and Dopplers Effect', 'Interference', '20', '"+ getVideoUri("video") +"')," +
                "(369, 'Class 11', 'Physics', '15. WAVES', '15.7 Interference and Dopplers Effect', 'Dopplers Effect', '15', '"+ getVideoUri("video") +"')," +
                "(370, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.1 GENERAL PROPERTIES AND CRYSTALLOGRAPHY', 'Properties of a solid', '8', '"+ getVideoUri("video") +"')," +
                "(371, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.1 GENERAL PROPERTIES AND CRYSTALLOGRAPHY', 'Crystalline and Amorphous Solids Classification', '7', '"+ getVideoUri("video") +"')," +
                "(372, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.1 GENERAL PROPERTIES AND CRYSTALLOGRAPHY', 'Types of unit cell Packing Fractions Elements of Symmetry', '10', '"+ getVideoUri("video") +"')," +
                "(373, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.2 Voids and Packing of Spheres', 'Voids', '10', '"+ getVideoUri("video") +"')," +
                "(374, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.2 Voids and Packing of Spheres', 'Density of Solid hcp and fcc Packing of Spheres', '8', '"+ getVideoUri("video") +"')," +
                "(375, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.3 Ionic Crystals', 'Coordination Number of sc fcc and bcc Unit Cells', '10', '"+ getVideoUri("video") +"')," +
                "(376, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.3 Ionic Crystals', 'Ionic Crystals', '8', '"+ getVideoUri("video") +"')," +
                "(377, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.4 Defects and Imperfections', 'Defects and Imperfections in a Solid  Stoichiometric Defects', '8', '"+ getVideoUri("video") +"')," +
                "(378, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.4 Defects and Imperfections', 'Non-stiochiometric Defects', '8', '"+ getVideoUri("video") +"')," +
                "(379, 'Class 12', 'Chemistry', '1. SOLID STATE', '1.4 Defects and Imperfections', 'Electrical Properties of Solid', '7', '"+ getVideoUri("video") +"')," +
                "(380, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.1 Concentration Terms', 'Expressing Concentration Terms', '10', '"+ getVideoUri("video") +"')," +
                "(381, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.1 Concentration Terms', 'Henrys Law', '10', '"+ getVideoUri("video") +"')," +
                "(382, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.2 Types of Solutions', 'Vapour Pressure', '15', '"+ getVideoUri("video") +"')," +
                "(383, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.2 Types of Solutions', 'Types of Solutions and Raouts Law', '15', '"+ getVideoUri("video") +"')," +
                "(384, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.3 Colligative Properties', 'Colligative Properties  Relating Raoults Law with Daltons Law', '8', '"+ getVideoUri("video") +"')," +
                "(385, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.3 Colligative Properties', 'Relative Lowering of V.T.', '9', '"+ getVideoUri("video") +"')," +
                "(386, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.3 Colligative Properties', 'Elevation in Boiling Point', '10', '"+ getVideoUri("video") +"')," +
                "(387, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.3 Colligative Properties', 'Depression in Freezing Point', '10', '"+ getVideoUri("video") +"')," +
                "(388, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.4 Osmotic Pressure and Vant Hoff Factor', 'Osmotic Pressure', '10', '"+ getVideoUri("video") +"')," +
                "(389, 'Class 12', 'Chemistry', '2. SOLUTIONS', '2.4 Osmotic Pressure and Vant Hoff Factor', 'Vant Hoff Factor', '10', '"+ getVideoUri("video") +"')," +
                "(390, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.1 Electrolytic Conductances', 'Specific Conductance and Terms', '10', '"+ getVideoUri("video") +"')," +
                "(391, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.1 Electrolytic Conductances', 'Equivalent-Conductance', '8', '"+ getVideoUri("video") +"')," +
                "(392, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.2 Equivalent Conductance at Infinite Dilution', 'Equivalent Conductance at Infinite Dilute', '10', '"+ getVideoUri("video") +"')," +
                "(393, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.3 Electrolysis', 'Basics', '10', '"+ getVideoUri("video") +"')," +
                "(394, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.3 Electrolysis', 'Laws of Electrolysis', '10', '"+ getVideoUri("video") +"')," +
                "(395, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.4 Electrochemical Cells', 'Basics', '8', '"+ getVideoUri("video") +"')," +
                "(396, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.4 Electrochemical Cells', 'Cell Potentials and Cell emf', '9', '"+ getVideoUri("video") +"')," +
                "(397, 'Class 12', 'Chemistry', '3. ELECTROCHEMISTRY', '3.4 Electrochemical Cells', 'Nernst Equation', '10', '"+ getVideoUri("video") +"')," +
                "(398, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.1 Rate of Reaction', 'Velocity of Reaction', '10', '"+ getVideoUri("video") +"')," +
                "(399, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.1 Rate of Reaction', 'Rate Depiction', '8', '"+ getVideoUri("video") +"')," +
                "(400, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.2 Factors Affecting Rate of Reaction', 'Factors Affecting Rate of Reaction', '10', '"+ getVideoUri("video") +"')," +
                "(401, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.3 Rate Kinetics', 'Rate Kinetics', '15', '"+ getVideoUri("video") +"')," +
                "(402, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.3 Rate Kinetics', 'Orders of Reaction', '15', '"+ getVideoUri("video") +"')," +
                "(403, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.4 Nuclear Chemistry', 'Radioactivity', '10', '"+ getVideoUri("video") +"')," +
                "(404, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.4 Nuclear Chemistry', 'Laws of Radioactivity', '10', '"+ getVideoUri("video") +"')," +
                "(405, 'Class 12', 'Chemistry', '4. CHEMICAL KINETICS', '4.5 First Order Kinetics', 'Pressure variations of first Order Reactions', '10', '"+ getVideoUri("video") +"')," +
                "(406, 'Class 12', 'Chemistry', '5. SURFACE CHEMISTRY', '5.1 Adsorption', 'Basics', '10', '"+ getVideoUri("video") +"')," +
                "(407, 'Class 12', 'Chemistry', '5. SURFACE CHEMISTRY', '5.1 Adsorption', 'Adsorption-||', '10', '"+ getVideoUri("video") +"')," +
                "(408, 'Class 12', 'Chemistry', '5. SURFACE CHEMISTRY', '5.2 Catalysis', 'Catalysis', '10', '"+ getVideoUri("video") +"')," +
                "(409, 'Class 12', 'Chemistry', '5. SURFACE CHEMISTRY', '5.3 Colloids', 'Colloids', '10', '"+ getVideoUri("video") +"')," +
                "(410, 'Class 12', 'Chemistry', '5. SURFACE CHEMISTRY', '5.3 Colloids', 'Purification of Colloides', '10', '"+ getVideoUri("video") +"')," +
                "(411, 'Class 12', 'Chemistry', '5. SURFACE CHEMISTRY', '5.3 Colloids', 'Properties of Colloides', '10', '"+ getVideoUri("video") +"')," +
                "(412, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Electronic Configuration and Comparsion of Physical Properties', '10', '"+ getVideoUri("video") +"')," +
                "(413, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Oxides', '10', '"+ getVideoUri("video") +"')," +
                "(414, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Hydrides and Sulphides', '15', '"+ getVideoUri("video") +"')," +
                "(415, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Nitrogen and Some of its Compounds', '15', '"+ getVideoUri("video") +"')," +
                "(416, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Ammonia', '20', '"+ getVideoUri("video") +"')," +
                "(417, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Oxyacides of N', '10', '"+ getVideoUri("video") +"')," +
                "(418, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Oxidation of Compounds and Metals by HNO3', '8', '"+ getVideoUri("video") +"')," +
                "(419, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.1 Group-15 Elements', 'Extraction of Phosphours', '15', '"+ getVideoUri("video") +"')," +
                "(420, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.2 Group-16 Elements', 'Electronic Configuration and Comparison of Physical Properties', '20', '"+ getVideoUri("video") +"')," +
                "(421, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.2 Group-16 Elements', 'Hybrides and Oxides', '15', '"+ getVideoUri("video") +"')," +
                "(422, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.2 Group-16 Elements', 'Halides Oxygen and Ozone', '18', '"+ getVideoUri("video") +"')," +
                "(423, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.2 Group-16 Elements', 'Sulphur and its Compounds like SO2 H2S', '18', '"+ getVideoUri("video") +"')," +
                "(424, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.2 Group-16 Elements', 'Oxyacides of Sulphur', '16', '"+ getVideoUri("video") +"')," +
                "(425, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.2 Group-16 Elements', 'Peroxydisulphuric Acid Preparation and Reactions of H2SO4', '10', '"+ getVideoUri("video") +"')," +
                "(426, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.3 Group-17 Elements', 'Fluorine', '10', '"+ getVideoUri("video") +"')," +
                "(427, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.3 Group-17 Elements', 'Preparation and Reaction of CL2 BR2', '12', '"+ getVideoUri("video") +"')," +
                "(428, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.3 Group-17 Elements', 'Reaction of Br2 I2 and Some  Reactions of I2', '12', '"+ getVideoUri("video") +"')," +
                "(429, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.3 Group-17 Elements', 'Oxyacides of Halogens and Oxides of Halogens', '12', '"+ getVideoUri("video") +"')," +
                "(430, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.3 Group-17 Elements', 'Interhalogen Compounds and Pseudohalogens', '14', '"+ getVideoUri("video") +"')," +
                "(431, 'Class 12', 'Chemistry', '6. THE P-BLOCK ELEMENTS', '6.3 Group-17 Elements', 'Group 18 Elements', '10', '"+ getVideoUri("video") +"')," +
                "(432, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Introduction  Ores and Minerals', '10', '"+ getVideoUri("video") +"')," +
                "(433, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Concentration of the Ore', '10', '"+ getVideoUri("video") +"')," +
                "(434, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Pyrometallurgy and Hydrometallurgy', '10', '"+ getVideoUri("video") +"')," +
                "(435, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Smelting', '10', '"+ getVideoUri("video") +"')," +
                "(436, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Thermodynamics of Reduction', '10', '"+ getVideoUri("video") +"')," +
                "(437, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Electrolytic Reduction and Refining of Metals', '10', '"+ getVideoUri("video") +"')," +
                "(438, 'Class 12', 'Chemistry', '7. GENERAL PRINCIPLES AND  ISOLATION OF ELEMENTS', '7.1 Ores and Minerals', 'Electrolytic Reduction of Ag and Au', '10', '"+ getVideoUri("video") +"')," +
                "(439, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.1 d-Block Elements', '3d 4d and 5d Transition Elements', '10', '"+ getVideoUri("video") +"')," +
                "(440, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.1 d-Block Elements', 'Physical Properties of d-Block Elements', '8', '"+ getVideoUri("video") +"')," +
                "(441, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.1 d-Block Elements', 'Oxidation State and Density', '10', '"+ getVideoUri("video") +"')," +
                "(442, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.1 d-Block Elements', 'Magnetic Properties', '10', '"+ getVideoUri("video") +"')," +
                "(443, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.2 Compounds of d-Block Elements', 'Potassium Permanganate', '8', '"+ getVideoUri("video") +"')," +
                "(444, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.2 Compounds of d-Block Elements', 'Potassium Dichromate Silver Nitrate', '10', '"+ getVideoUri("video") +"')," +
                "(445, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.2 Compounds of d-Block Elements', 'Zinc Sulphate Zinc Oxide and Zinc Chloride', '15', '"+ getVideoUri("video") +"')," +
                "(446, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.2 Compounds of d-Block Elements', 'CuSo4x5H2o Cucl2 FeCL3', '15', '"+ getVideoUri("video") +"')," +
                "(447, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.3 f-Block Elements', '4f-inner Transition Series', '10', '"+ getVideoUri("video") +"')," +
                "(448, 'Class 12', 'Chemistry', '8. THE D & F BLOCK ELEMENTS', '8.3 f-Block Elements', '5f-inner Transition Series', '10', '"+ getVideoUri("video") +"')," +
                "(449, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.1 Nomenclature of Co-ordinate Compounds', 'Introduction', '10', '"+ getVideoUri("video") +"')," +
                "(450, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.1 Nomenclature of Co-ordinate Compounds', 'Ligands', '10', '"+ getVideoUri("video") +"')," +
                "(451, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.1 Nomenclature of Co-ordinate Compounds', 'Types of Complexes', '10', '"+ getVideoUri("video") +"')," +
                "(452, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.1 Nomenclature of Co-ordinate Compounds', 'Nomenclature of Co-ordinate Compounds', '10', '"+ getVideoUri("video") +"')," +
                "(453, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.2 Bonding in complexes', 'Valence Bond Theory', '10', '"+ getVideoUri("video") +"')," +
                "(454, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.2 Bonding in complexes', 'Crystal Field Theory', '10', '"+ getVideoUri("video") +"')," +
                "(455, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.3 Isomerism in Co-ordinate Compounds', 'Structural Isomerism', '15', '"+ getVideoUri("video") +"')," +
                "(456, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.3 Isomerism in Co-ordinate Compounds', 'Stereo-isomerism', '15', '"+ getVideoUri("video") +"')," +
                "(457, 'Class 12', 'Chemistry', '9. CO-ORDINATION COMPOUNDS', '9.3 Isomerism in Co-ordinate Compounds', 'Stability of Co-ordinate Compounds', '18', '"+ getVideoUri("video") +"')," +
                "(458, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.1 Introduction Classification and Preparations of Haloalkanes and Haloarenes', 'Classification of Haloalkanes and Haloarenes', '15', '"+ getVideoUri("video") +"')," +
                "(459, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.1 Introduction Classification and Preparations of Haloalkanes and Haloarenes', 'IUPAC Nomenclature and Preparation of Monohaloalkanes from Alcohols', '20', '"+ getVideoUri("video") +"')," +
                "(460, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.2 Other Methods of Preparation of Haloalkanes and Haloarenes', 'Halogen Exchange also called Swarts Reaction from Alkenes', '15', '"+ getVideoUri("video") +"')," +
                "(461, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.2 Other Methods of Preparation of Haloalkanes and Haloarenes', 'Hunsdicker Reaction Allyl and Benzyl Halides Vinyl Halides', '10', '"+ getVideoUri("video") +"')," +
                "(462, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.2 Other Methods of Preparation of Haloalkanes and Haloarenes', 'Dihalides Preparation of Haloarenes', '10', '"+ getVideoUri("video") +"')," +
                "(463, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.2 Other Methods of Preparation of Haloalkanes and Haloarenes', 'Properties of Haloalkanes', '18', '"+ getVideoUri("video") +"')," +
                "(464, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.3 Optical Isomerism Nucleophillic Substitution Reactions and Elimination Reactions', 'Optical isomerism', '20', '"+ getVideoUri("video") +"')," +
                "(465, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.3 Optical Isomerism Nucleophillic Substitution Reactions and Elimination Reactions', 'Nucleophillic Substituation', '30', '"+ getVideoUri("video") +"')," +
                "(466, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.3 Optical Isomerism Nucleophillic Substitution Reactions and Elimination Reactions', 'Elimination Reaction by E2 and E1', '30', '"+ getVideoUri("video") +"')," +
                "(467, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.3 Optical Isomerism Nucleophillic Substitution Reactions and Elimination Reactions', 'Amlident Nucleophile Reactions with Metals Reduction Reactions', '20', '"+ getVideoUri("video") +"')," +
                "(468, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.4 Reaction of Aryl Halides', 'Reactions of Aryl Halides', '20', '"+ getVideoUri("video") +"')," +
                "(469, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.4 Reaction of Aryl Halides', 'Conditions Favouring Benzyne', '13', '"+ getVideoUri("video") +"')," +
                "(470, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.4 Reaction of Aryl Halides', 'Nucleophillic Aromatic Substitution', '15', '"+ getVideoUri("video") +"')," +
                "(471, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.4 Reaction of Aryl Halides', 'Wurtz Fittig Reactions Electrophillic Aromatic Substitution Reactions', '15', '"+ getVideoUri("video") +"')," +
                "(472, 'Class 12', 'Chemistry', '10. HALOALKANES AND HALOARENES', '10.4 Reaction of Aryl Halides', 'Reactions of Vinyl and Allyl Halides and Ployhalogen Compounds', '20', '"+ getVideoUri("video") +"')," +
                "(473, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.1 Alcohols', 'Classification of Alchols', '20', '"+ getVideoUri("video") +"')," +
                "(474, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.1 Alcohols', 'Distinguish between Primary Secondary Tertiary Alcohols', '20', '"+ getVideoUri("video") +"')," +
                "(475, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.1 Alcohols', 'Preparation of Alcohols', '15', '"+ getVideoUri("video") +"')," +
                "(476, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.1 Alcohols', 'Chemical Properties of Alcohols', '20', '"+ getVideoUri("video") +"')," +
                "(477, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.1 Alcohols', 'Preparation of Phenol', '8', '"+ getVideoUri("video") +"')," +
                "(478, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.2 Phenol', 'Acidity of Phenol', '20', '"+ getVideoUri("video") +"')," +
                "(479, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.2 Phenol', 'Chemical Properties of Phenol', '20', '"+ getVideoUri("video") +"')," +
                "(480, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.3 Ethers', 'Preparation of Ethers', '15', '"+ getVideoUri("video") +"')," +
                "(481, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.3 Ethers', 'Chemical Properties of Ethers', '14', '"+ getVideoUri("video") +"')," +
                "(482, 'Class 12', 'Chemistry', '11.ALCOHOLS PHENOLS AND ETHERS', '11.3 Ethers', 'Epoxides', '12', '"+ getVideoUri("video") +"')," +
                "(483, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Preparation of Aldehyde and Ketone', '10', '"+ getVideoUri("video") +"')," +
                "(484, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Chemical Properties of Aldehydes', '20', '"+ getVideoUri("video") +"')," +
                "(485, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Substitution Reactions', '15', '"+ getVideoUri("video") +"')," +
                "(486, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Cannizzaro Reaction', '12', '"+ getVideoUri("video") +"')," +
                "(487, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Oxidation', '15', '"+ getVideoUri("video") +"')," +
                "(488, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Aldol Condensation', '13', '"+ getVideoUri("video") +"')," +
                "(489, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Cross Aldol Condensation', '25', '"+ getVideoUri("video") +"')," +
                "(490, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Intramolecular Aldol Condensation', '20', '"+ getVideoUri("video") +"')," +
                "(491, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Haloform Reaction', '10', '"+ getVideoUri("video") +"')," +
                "(492, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.1 Aldehydes and Ketones', 'Perkin Reaction', '13', '"+ getVideoUri("video") +"')," +
                "(493, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.2 Carboxylic Acids', 'Acidic Nature', '15', '"+ getVideoUri("video") +"')," +
                "(494, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.2 Carboxylic Acids', 'Preparation of Carboxylic Acids', '20', '"+ getVideoUri("video") +"')," +
                "(495, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.2 Carboxylic Acids', 'Chemical Properties of Carboxylic Acids', '20', '"+ getVideoUri("video") +"')," +
                "(496, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.3 Acid Derivatives', 'Acetyl Chloride and Acetic Anhydride', '10', '"+ getVideoUri("video") +"')," +
                "(497, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.3 Acid Derivatives', 'Ethyl Acetate', '20', '"+ getVideoUri("video") +"')," +
                "(498, 'Class 12', 'Chemistry', '12. ALDEHYDES KETONES AND CARBOXYLIC ACIDS', '12.3 Acid Derivatives', 'Acetamide', '12', '"+ getVideoUri("video") +"')," +
                "(499, 'Class 12', 'Chemistry', '13. AMINES', '13.1 Naming of Amines and Preparation of Amines', 'Classification of Amines and Hetrocyclic Amines', '10', '"+ getVideoUri("video") +"')," +
                "(500, 'Class 12', 'Chemistry', '13. AMINES', '13.1 Naming of Amines and Preparation of Amines', 'Preparation of Amines-Alkylation of NH3 Reduction of Alkyl Azide', '15', '"+ getVideoUri("video") +"')," +
                "(501, 'Class 12', 'Chemistry', '13. AMINES', '13.1 Naming of Amines and Preparation of Amines', 'Reduction of Nitroalkanes', '20', '"+ getVideoUri("video") +"')," +
                "(502, 'Class 12', 'Chemistry', '13. AMINES', '13.2 Some Other Methods of Preparation of Amines', 'Reductions of Cynides Isocynides Oximes', '20', '"+ getVideoUri("video") +"')," +
                "(503, 'Class 12', 'Chemistry', '13. AMINES', '13.2 Some Other Methods of Preparation of Amines', 'Properties of Amines', '15', '"+ getVideoUri("video") +"')," +
                "(504, 'Class 12', 'Chemistry', '13. AMINES', '13.2 Some Other Methods of Preparation of Amines', 'Chemical Properties : Carbylamine reaction', '15', '"+ getVideoUri("video") +"')," +
                "(505, 'Class 12', 'Chemistry', '13. AMINES', '13.2 Some Other Methods of Preparation of Amines', 'Reaction with HNO2', '12', '"+ getVideoUri("video") +"')," +
                "(506, 'Class 12', 'Chemistry', '13. AMINES', '13.3 Some other Properties of Amines and Other Nitrogen Containing Compounds', 'Reaction of 2 degree and 3 degree Amines with HNO2', '20', '"+ getVideoUri("video") +"')," +
                "(507, 'Class 12', 'Chemistry', '13. AMINES', '13.3 Some other Properties of Amines and Other Nitrogen Containing Compounds', 'Coupling Reaction', '15', '"+ getVideoUri("video") +"')," +
                "(508, 'Class 12', 'Chemistry', '13. AMINES', '13.3 Some other Properties of Amines and Other Nitrogen Containing Compounds', 'Rearrangement Reaction of Aromatic Amines', '20', '"+ getVideoUri("video") +"')," +
                "(509, 'Class 12', 'Chemistry', '13. AMINES', '13.3 Some other Properties of Amines and Other Nitrogen Containing Compounds', 'Electrophillic Aromatic Substitution of Aromatic Amines', '20', '"+ getVideoUri("video") +"')," +
                "(510, 'Class 12', 'Chemistry', '13. AMINES', '13.3 Some other Properties of Amines and Other Nitrogen Containing Compounds', 'Nitro Componds Cyanides and Isocyanides', '15', '"+ getVideoUri("video") +"')," +
                "(511, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.1 Carbohydrates', 'Introduction and Classification of Carbohydrates', '15', '"+ getVideoUri("video") +"')," +
                "(512, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.1 Carbohydrates', 'Aldoses and Ketoses', '20', '"+ getVideoUri("video") +"')," +
                "(513, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.1 Carbohydrates', 'Mutraotation', '15', '"+ getVideoUri("video") +"')," +
                "(514, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.2 Properties of Carbohydrates', 'Reaction with PhNHNH2 Reaction with Br2CH2O Reaction with HCN', '20', '"+ getVideoUri("video") +"')," +
                "(515, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.2 Properties of Carbohydrates', 'Disachharides', '20', '"+ getVideoUri("video") +"')," +
                "(516, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.2 Properties of Carbohydrates', 'Polysachharides', '20', '"+ getVideoUri("video") +"')," +
                "(517, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.3 a-Amino Acids', 'Structure of a-Amino Acides', '10', '"+ getVideoUri("video") +"')," +
                "(518, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.3 a-Amino Acids', 'Isoelectric Point', '12', '"+ getVideoUri("video") +"')," +
                "(519, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.3 a-Amino Acids', 'Other Methods of Preparations of a-Amino Acids', '8', '"+ getVideoUri("video") +"')," +
                "(520, 'Class 12', 'Chemistry', '14. BIOMOLECULES', '14.3 a-Amino Acids', 'Ninhydrin Test Polypeptides and Proteins', '12', '"+ getVideoUri("video") +"')," +
                "(521, 'Class 12', 'Chemistry', '15. POLYMERS', '15.1 Classification of Polymers', 'Introduction-Monomers Homopolymers and Copolymers', '15', '"+ getVideoUri("video") +"')," +
                "(522, 'Class 12', 'Chemistry', '15. POLYMERS', '15.1 Classification of Polymers', 'Classification of Polymers', '20', '"+ getVideoUri("video") +"')," +
                "(523, 'Class 12', 'Chemistry', '15. POLYMERS', '15.1 Classification of Polymers', 'Preparations of Polymers Condensation Polymerisation', '10', '"+ getVideoUri("video") +"')," +
                "(524, 'Class 12', 'Chemistry', '15. POLYMERS', '15.1 Classification of Polymers', 'Addition Polymerisation', '10', '"+ getVideoUri("video") +"')," +
                "(525, 'Class 12', 'Chemistry', '15. POLYMERS', '15.2 Addition Polymer intiated by Other Method', 'Chain transfer Agents Cationic Polymer Anionin Polymer', '10', '"+ getVideoUri("video") +"')," +
                "(526, 'Class 12', 'Chemistry', '15. POLYMERS', '15.2 Addition Polymer intiated by Other Method', 'Ziegler Natta Catalyst Rubber Natural', '12', '"+ getVideoUri("video") +"')," +
                "(527, 'Class 12', 'Chemistry', '15. POLYMERS', '15.2 Addition Polymer intiated by Other Method', 'Synthetic Rubber', '15', '"+ getVideoUri("video") +"')," +
                "(528, 'Class 12', 'Chemistry', '15. POLYMERS', '15.2 Addition Polymer intiated by Other Method', 'Nylon', '15', '"+ getVideoUri("video") +"')," +
                "(529, 'Class 12', 'Chemistry', '15. POLYMERS', '15.2 Addition Polymer intiated by Other Method', 'Cross-linked Polymers Biodegradable Polymers', '13', '"+ getVideoUri("video") +"')," +
                "(530, 'Class 12', 'Chemistry', '16.CHEMISTRY IN EVERYDAY LIFE', '16.1 Chemistry in everday Life', 'Drugs', '10', '"+ getVideoUri("video") +"')," +
                "(531, 'Class 12', 'Chemistry', '16.CHEMISTRY IN EVERYDAY LIFE', '16.1 Chemistry in everday Life', 'Analgesics Antibiotics Antiseptics', '10', '"+ getVideoUri("video") +"')," +
                "(532, 'Class 12', 'Chemistry', '16.CHEMISTRY IN EVERYDAY LIFE', '16.1 Chemistry in everday Life', 'Chemical in Foods Soaps and Detergent', '10', '"+ getVideoUri("video") +"')," +
                "(533, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.1 ELETROSTATICS', 'CHARGE AND COULOMBS LAW:CHARGE AND ITS PROPERTIES', '10', '"+ getVideoUri("video") +"')," +
                "(534, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.1 ELETROSTATICS', 'FORCE SUPERPOSITION AND ELECTRIC FIELD:GROUP OF POINT CHARGES', '8', '"+ getVideoUri("video") +"')," +
                "(535, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.1 ELETROSTATICS', 'COULOMBS LAW AND FIELD VECTORIALLY', '6', '"+ getVideoUri("video") +"')," +
                "(536, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.2 ELECTROSTATICS', 'PLOTTING AND E AND BASIC INTEGRATION:NULL POINTS', '8', '"+ getVideoUri("video") +"')," +
                "(537, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.2 ELECTROSTATICS', 'LINEAR,SURFACE AND VOLUME DISTRIBUTION', '10', '"+ getVideoUri("video") +"')," +
                "(538, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.2 ELECTROSTATICS', 'E DUE TO INFINITELY LONG CHARGE,E DUE TO RING', '10', '"+ getVideoUri("video") +"')," +
                "(539, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.3 ELECTROSTATICS', 'E DUE TO RING', '10', '"+ getVideoUri("video") +"')," +
                "(540, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.3 ELECTROSTATICS', 'E DUE TO NON-UNIFORM RING', '5', '"+ getVideoUri("video") +"')," +
                "(541, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.3 ELECTROSTATICS', 'ANNULAR DISK F=Qe', '6', '"+ getVideoUri("video") +"')," +
                "(542, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.4 FIELD LINES & FULX', 'FIELD LINES : LINES OF FORCE', '12', '"+ getVideoUri("video") +"')," +
                "(543, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.4 FIELD LINES & FULX', 'INTRODUCTION TO FLUX', '6', '"+ getVideoUri("video") +"')," +
                "(544, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.5 ELECROSTATICS', 'FLUX IN CONSTANT FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(545, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.5 ELECROSTATICS', 'GUESSS LAW', '10', '"+ getVideoUri("video") +"')," +
                "(546, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.5 ELECROSTATICS', 'ELECTRIC FIELD USING GUASSS LAW', '10', '"+ getVideoUri("video") +"')," +
                "(547, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.6 ELECTROSTATICS', ' E DUE TO SPHERE', '12', '"+ getVideoUri("video") +"')," +
                "(548, 'Class 12', 'Physics', '1 ELECTRIC CHARGES AND FIELD', '1.6 ELECTROSTATICS', ' E DUE TO CYLINDER', '10', '"+ getVideoUri("video") +"')," +
                "(549, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.1ELECTROSTATICS', 'POTENTIAL DUE TO POINT CHARGE', '5', '"+ getVideoUri("video") +"')," +
                "(550, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.1ELECTROSTATICS', 'V DUE TO HOLLOW', '10', '"+ getVideoUri("video") +"')," +
                "(551, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.1ELECTROSTATICS', 'PD DUE TO LINE CHARGE', '10', '"+ getVideoUri("video") +"')," +
                "(552, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.2ELECTROSTATICS', 'GRADIENT:PARTIAL DERIVATIVE', '10', '"+ getVideoUri("video") +"')," +
                "(553, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.2ELECTROSTATICS', 'PHYSICAL MEANING OF GRADIENT', '10', '"+ getVideoUri("video") +"')," +
                "(554, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.2ELECTROSTATICS', 'CONVERSION OF E TO V', '10', '"+ getVideoUri("video") +"')," +
                "(555, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.3ELECTROSTATICS', 'EQUIPOTENTIAL SURFACE', '10', '"+ getVideoUri("video") +"')," +
                "(556, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.3ELECTROSTATICS', 'PLOTTING FIELD LINES FROM EQUIPOTENTIAL SURFACE', '10', '"+ getVideoUri("video") +"')," +
                "(557, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.4 ELECTROSTATICS', 'CONDUCTORS:UNCHARGED SOLID CONDUCTOR IN EXTERNAL FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(558, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.4 ELECTROSTATICS', 'UNCHARGED HOLLOW CONDUCTOR IN EXTERNAL FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(559, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.5ELECTROSTATICS', 'REDISTRIBUTION OF CHARGE IN SPHERE', '10', '"+ getVideoUri("video") +"')," +
                "(560, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.5ELECTROSTATICS', 'REDISTRIBUTION OF CHARGE IN PARALLEL PLATES', '10', '"+ getVideoUri("video") +"')," +
                "(561, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.6 ELECTROSTATICS', 'DIPOLE,ITS FIELD AND POTENTIAL:GROUNDING', '10', '"+ getVideoUri("video") +"')," +
                "(562, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.6 ELECTROSTATICS', 'DIPOLE,ITS FIELD AND POTENTIAL:ELECTROSTATIC PRESSURE', '10', '"+ getVideoUri("video") +"')," +
                "(563, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.7 ELECTROSTATICS', 'FORCE AND TORQUE ON DIPOLE IN UNIFORM FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(564, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.7 ELECTROSTATICS', 'PE OF DIPOLE IN UNIFORM AND NON-UNIFORM FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(565, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.7 ELECTROSTATICS', 'MOTION IN ELECTRIC FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(566, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.8 ELECROSTATICS', 'BASIC CONCEPT OF CHARGE AND POTENTIAL IN CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(567, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.8 ELECROSTATICS', 'SERIES AND PARALLEL COMBINATION', '10', '"+ getVideoUri("video") +"')," +
                "(568, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.9 ELECTROSTATICS', 'WHEATSTONE BRIDGE', '10', '"+ getVideoUri("video") +"')," +
                "(569, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.9 ELECTROSTATICS', 'SIMPLE CIRCUITS,WORKDONE BY BATTERY,ENERGY STORED IN CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(570, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.10 ELECTROSTATICS', 'COMPLEX CIRCUIT', '10', '"+ getVideoUri("video") +"')," +
                "(571, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.10 ELECTROSTATICS', 'FORCE BETWEEN PLATES OF CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(572, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.10 ELECTROSTATICS', 'SELF-ENERGY AND ENERGY DENSITY', '10', '"+ getVideoUri("video") +"')," +
                "(573, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.11ELECTROSTATICS', 'DIELECTRIC DEFINITION,ELECTRIC FIELD INSIDE DIELECTRIC', '10', '"+ getVideoUri("video") +"')," +
                "(574, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.11ELECTROSTATICS', 'EFFECT OF DIELECTRIC IN CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(575, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.12 ELECTROSTATICS', 'DIELECTRIC CIRCUIT,CAPACITIVE CURRENT', '10', '"+ getVideoUri("video") +"')," +
                "(576, 'Class 12', 'Physics', '2 ELECTROSTATIC POTENTIAL AND CAPACITANCE', '2.12 ELECTROSTATICS', 'INDUCED CHARGE,FORCE ON DIELECTRIC', '10', '"+ getVideoUri("video") +"')," +
                "(577, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.1 ELECTRIC CURRENT IN CONDUCTORS', 'ELECTRIC CURRENT AND CURRENT DENSITY', '10', '"+ getVideoUri("video") +"')," +
                "(578, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.1 ELECTRIC CURRENT IN CONDUCTORS', 'DRIFT VELOCITY', '10', '"+ getVideoUri("video") +"')," +
                "(579, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.1 ELECTRIC CURRENT IN CONDUCTORS', 'OHMS LAW FROM DRIFT MODEL', '10', '"+ getVideoUri("video") +"')," +
                "(580, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.2 ELECTRICAL RESISTANCES', 'CALCULATION OF RESISTANCE OF A CONDUCTOR', '10', '"+ getVideoUri("video") +"')," +
                "(581, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.2 ELECTRICAL RESISTANCES', 'VARIATION OF RESISTANCE WITH TEMPERATURE', '10', '"+ getVideoUri("video") +"')," +
                "(582, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.2 ELECTRICAL RESISTANCES', 'SERIES AND PARALLEL COMBINATION OF RESISTANCES', '10', '"+ getVideoUri("video") +"')," +
                "(583, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.3 SPECIAL COMBINATIONS OF RESISTANCES', 'MIXED COMBINATION OF RESISTANCES', '10', '"+ getVideoUri("video") +"')," +
                "(584, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.3 SPECIAL COMBINATIONS OF RESISTANCES', 'SYMMETRY-BASED COMBINATIONS', '10', '"+ getVideoUri("video") +"')," +
                "(585, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.3 SPECIAL COMBINATIONS OF RESISTANCES', 'MIXED COMBINATION OF RESISTANCES', '10', '"+ getVideoUri("video") +"')," +
                "(586, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.4 GENERAL DC CIRCUITS', 'NODE POTENIAL METHOD', '10', '"+ getVideoUri("video") +"')," +
                "(587, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.4 GENERAL DC CIRCUITS', 'KIRCHOFFS LAWS', '10', '"+ getVideoUri("video") +"')," +
                "(588, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.5 ENERGY CALCULATIONS IN DC CIRCUITS', 'EMF AND INTERNAL RESISTANCE OF A CELL', '10', '"+ getVideoUri("video") +"')," +
                "(589, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.5 ENERGY CALCULATIONS IN DC CIRCUITS', 'HEATING EFFECTS OF CURRENT', '10', '"+ getVideoUri("video") +"')," +
                "(590, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.6 ELECTRIC CELLS', ' MAXIMUM POWER TRANSFER THEORM', '10', '"+ getVideoUri("video") +"')," +
                "(591, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.6 ELECTRIC CELLS', 'COMBINATIONS OF CELLS', '10', '"+ getVideoUri("video") +"')," +
                "(592, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.6 ELECTRIC CELLS', 'TERMINAL VOLTAGE IN DIFFERENT CONNECTIONS OF A CELL', '10', '"+ getVideoUri("video") +"')," +
                "(593, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.7MEASUREMENT OF ELECTRICAL QUANTITIES', 'MEASUREMENT OF CURRENT AND VOLTAGE', '10', '"+ getVideoUri("video") +"')," +
                "(594, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.7MEASUREMENT OF ELECTRICAL QUANTITIES', 'MEASUREMENT OF RESISTANCE', '10', '"+ getVideoUri("video") +"')," +
                "(595, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.7MEASUREMENT OF ELECTRICAL QUANTITIES', 'MEASUREMENT OF EMF AND INTERNAL RESISTANCE OF A CELL', '10', '"+ getVideoUri("video") +"')," +
                "(596, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.8 R-C CIRCUITS', 'CHARGING OF A CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(597, 'Class 12', 'Physics', '3 CURRENT ELECTRICITY', '3.8 R-C CIRCUITS', 'DISCHARGING OF A CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(598, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.1 MAGNETIC FIELD AND BIOT-SAVARTS LAW', 'FIELD DUE TO POINT CHARGE', '10', '"+ getVideoUri("video") +"')," +
                "(599, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.1 MAGNETIC FIELD AND BIOT-SAVARTS LAW', 'BIOT-SAVARTS LAW', '10', '"+ getVideoUri("video") +"')," +
                "(600, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.2 APPLICATION OF BIOT-SAVARTS LAW AND INTRODUCTION TO AMPERES LAW', 'FIELD DUE TO CIRCULAR LOOPS', '10', '"+ getVideoUri("video") +"')," +
                "(601, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.2 APPLICATION OF BIOT-SAVARTS LAW AND INTRODUCTION TO AMPERES LAW', 'FIELD DUE TO SOIENOID AND AMPERES LAW', '10', '"+ getVideoUri("video") +"')," +
                "(602, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.3 APPLICATION OF AMPERES LAW', 'APPLICATION OF AMPERES LAW', '10', '"+ getVideoUri("video") +"')," +
                "(603, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.3 APPLICATION OF AMPERES LAW', 'APPLICATION OF AMPERES LAW(CONTD.)', '10', '"+ getVideoUri("video") +"')," +
                "(604, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.4 MAGNETIC FORCE ON POINT CHARGE', 'MAGNETIC FORCE', '10', '"+ getVideoUri("video") +"')," +
                "(605, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.4 MAGNETIC FORCE ON POINT CHARGE', 'MAGNETIC FORCE BETWEEN TWO POINT CHARGES', '10', '"+ getVideoUri("video") +"')," +
                "(606, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.4 MAGNETIC FORCE ON POINT CHARGE', 'MOTION OF CHARGED PARTICLE IN UNIFORM FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(607, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.5 MOTION OF POINT CHARGE IN ELECTROMAGNETIC FIELD', 'MOTION OF POINT CHARGE IN INFINITE AND FINITE MAGNETIC FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(608, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.5 MOTION OF POINT CHARGE IN ELECTROMAGNETIC FIELD', 'NON-UNIFORM FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(609, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.5 MOTION OF POINT CHARGE IN ELECTROMAGNETIC FIELD', 'LORENTZ FORCE', '10', '"+ getVideoUri("video") +"')," +
                "(610, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.6 FORCE ON POINT CHARGE(CONTD.) AND FORCE ON CONDUCTOR', 'LORENTZ FORCE(CONTD.)', '10', '"+ getVideoUri("video") +"')," +
                "(611, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.6 FORCE ON POINT CHARGE(CONTD.) AND FORCE ON CONDUCTOR', 'FORCE ON CONDUCTOR', '10', '"+ getVideoUri("video") +"')," +
                "(612, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.6 FORCE ON POINT CHARGE(CONTD.) AND FORCE ON CONDUCTOR', 'FORCE ON CONDUCTOR(CONTD.)', '10', '"+ getVideoUri("video") +"')," +
                "(613, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.7 MAGNETIC FIELD', ' MAGNETIC DIPOLE MOMENT', '10', '"+ getVideoUri("video") +"')," +
                "(614, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.7 MAGNETIC FIELD', 'CIRCULATING CHARGE', '10', '"+ getVideoUri("video") +"')," +
                "(615, 'Class 12', 'Physics', '4 MOVING CHARGES AND MAGNETISM', '4.7 MAGNETIC FIELD', ' LOOP IN MAGNETIC FIELD AND DEVICES', '10', '"+ getVideoUri("video") +"')," +
                "(616, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.1 MAGNETISM OF MATTER', 'PERMANENT MAGNET', '6', '"+ getVideoUri("video") +"')," +
                "(617, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.1 MAGNETISM OF MATTER', 'FIELD AND POTENTIAL DUE TO PERMANENT MAGNET', '4', '"+ getVideoUri("video") +"')," +
                "(618, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.1 MAGNETISM OF MATTER', ' TERRESTRIAL MAGNETISM', '8', '"+ getVideoUri("video") +"')," +
                "(619, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.2 MAGNETISM OF MATTER(CONTD.)', 'MICROSCOPIC CAUSE OF MAGNETISM', '10', '"+ getVideoUri("video") +"')," +
                "(620, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.2 MAGNETISM OF MATTER(CONTD.)', 'TYPES OF MAGNETIC MATERIALS', '8', '"+ getVideoUri("video") +"')," +
                "(621, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.2 MAGNETISM OF MATTER(CONTD.)', 'MAGNETIC VECTORS', '7', '"+ getVideoUri("video") +"')," +
                "(622, 'Class 12', 'Physics', '5 MAGNETISM AND MATTER', '5.2 MAGNETISM OF MATTER(CONTD.)', 'MAGNETIC HYSTERSIS', '6', '"+ getVideoUri("video") +"')," +
                "(623, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.1 FARADAYSS LAW OF ELECTROMAGNETIC INDUCTION', 'MAGNETIC FIELD LINES AND MAGNETIC FLUX', '10', '"+ getVideoUri("video") +"')," +
                "(624, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.1 FARADAYSS LAW OF ELECTROMAGNETIC INDUCTION', 'FARADAYS LAW AND INDUCED EMF', '10', '"+ getVideoUri("video") +"')," +
                "(625, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.1 FARADAYSS LAW OF ELECTROMAGNETIC INDUCTION', 'CALCULATION OF INDUCED EMF', '10', '"+ getVideoUri("video") +"')," +
                "(626, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.2 FARADAYS LAW AND INDUCED ELECTRIC FIELD', 'CHARGE FLOW IN INDUCTION', '10', '"+ getVideoUri("video") +"')," +
                "(627, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.2 FARADAYS LAW AND INDUCED ELECTRIC FIELD', 'INDUCED ELECTRIC FIELD', '10', '"+ getVideoUri("video") +"')," +
                "(628, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.3 MOTIONAL EMF', 'MOTION EMF IN A ROD IN TRANSLATIONAL MOTION', '10', '"+ getVideoUri("video") +"')," +
                "(629, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.3 MOTIONAL EMF', 'MOTIONAL EMF IN A BODY IN ROTATIONAL MOTION', '10', '"+ getVideoUri("video") +"')," +
                "(630, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.4 MOTIONAL EMF(CONTD.)', 'MOTIONAL EMF IN A SLIDER ON P-SHAPED RAILS', '10', '"+ getVideoUri("video") +"')," +
                "(631, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.4 MOTIONAL EMF(CONTD.)', 'MOTIONAL EMF IN A ROD CONNECTED TO CAPACITOR', '10', '"+ getVideoUri("video") +"')," +
                "(632, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.4 MOTIONAL EMF(CONTD.)', 'MOTIONAL EMF IN A SLIDER(CONTD.)', '10', '"+ getVideoUri("video") +"')," +
                "(633, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.5 SELF-INDUCTION', 'SELF-INDUCTANCE', '10', '"+ getVideoUri("video") +"')," +
                "(634, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.5 SELF-INDUCTION', ' EMF INDUCED IN SELF-INDUCTION', '10', '"+ getVideoUri("video") +"')," +
                "(635, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.5 SELF-INDUCTION', ' ENERGY STORED IN AN INDUCTOR', '10', '"+ getVideoUri("video") +"')," +
                "(636, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.5 SELF-INDUCTION', 'COMBINATION OF INDUCTORS WITH NO MUTUAL INDUCTANCE', '10', '"+ getVideoUri("video") +"')," +
                "(637, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.6 TRANSIENT CURRENTS IN L-R CIRCUIT', ' GROWH AND DECAY OF CURRENT IN L-R CIRCUIT', '10', '"+ getVideoUri("video") +"')," +
                "(638, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.6 TRANSIENT CURRENTS IN L-R CIRCUIT', 'ENERGY CALCULATION IN AN L-R CIRCUIT', '10', '"+ getVideoUri("video") +"')," +
                "(639, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.6 TRANSIENT CURRENTS IN L-R CIRCUIT', 'TIME CONSTANT OF A COMPLEX R-L CIRCUITS', '10', '"+ getVideoUri("video") +"')," +
                "(640, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.7 MUTUAL INDUCTION AND L-C OSCILLATIONS', ' MUTUAL INDUCTANCE BETWEEN TWO COILS', '10', '"+ getVideoUri("video") +"')," +
                "(641, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.7 MUTUAL INDUCTION AND L-C OSCILLATIONS', 'COEFFICIENT OF COUPLING', '10', '"+ getVideoUri("video") +"')," +
                "(642, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.7 MUTUAL INDUCTION AND L-C OSCILLATIONS', 'L-C OSCILLATIONS', '10', '"+ getVideoUri("video") +"')," +
                "(643, 'Class 12', 'Physics', '6 ELECTROMAGNETIC INDUCTION', '6.7 MUTUAL INDUCTION AND L-C OSCILLATIONS', ' ENERGY CALCULATIOS IN L-C OSCILLATIONS', '10', '"+ getVideoUri("video") +"')," +
                "(644, 'Class 12', 'Physics', '7 ALTERNATING CURRENT', '7.1 ALTERNATING CURRENT', 'ALTERNATING CURRENT AND VOLTAGES AND THEIR AVERAGE VALUES', '8', '"+ getVideoUri("video") +"')," +
                "(645, 'Class 12', 'Physics', '8 ALTERNATING CURRENT', '7.1 ALTERNATING CURRENT', 'RMS CURRENT AND RMS VOLTAGE', '10', '"+ getVideoUri("video") +"')," +
                "(646, 'Class 12', 'Physics', '9 ALTERNATING CURRENT', '7.1 ALTERNATING CURRENT', 'AC THROUGH RESISTANCES,INDUCTANCE AND CAPACITANCE', '10', '"+ getVideoUri("video") +"')," +
                "(647, 'Class 12', 'Physics', '10 ALTERNATING CURRENT', '7.1 ALTERNATING CURRENT', 'SERIES L-C-R CIRCUIT', '12', '"+ getVideoUri("video") +"')," +
                "(648, 'Class 12', 'Physics', '11 ALTERNATING CURRENT', '7.2 RESONANCE IN AC CIRCUITS AND POWER CALCULATION', 'SERIES LCR CIRCUIT AND CONDITION FOR RESONANCE', '8', '"+ getVideoUri("video") +"')," +
                "(649, 'Class 12', 'Physics', '12 ALTERNATING CURRENT', '7.2 RESONANCE IN AC CIRCUITS AND POWER CALCULATION', 'SHARPNESS OF RESONANCE', '8', '"+ getVideoUri("video") +"')," +
                "(650, 'Class 12', 'Physics', '13 ALTERNATING CURRENT', '7.2 RESONANCE IN AC CIRCUITS AND POWER CALCULATION', 'PARALLEL LCR CIRCUIT AND CONDITION FOR RESONANCE', '8', '"+ getVideoUri("video") +"')," +
                "(651, 'Class 12', 'Physics', '14 ALTERNATING CURRENT', '7.2 RESONANCE IN AC CIRCUITS AND POWER CALCULATION', 'POWER IN AC CIRCUIT', '9', '"+ getVideoUri("video") +"')," +
                "(652, 'Class 12', 'Physics', '15 ALTERNATING CURRENT', '7.2 RESONANCE IN AC CIRCUITS AND POWER CALCULATION', 'TRANSFORMER', '9', '"+ getVideoUri("video") +"')," +
                "(653, 'Class 12', 'Physics', '8 ELECTROMAGNETIC WAVES', '8.1ELECTROMAGNETIC WAVES', 'ELECTROMAGNETIC WAVES', '10', '"+ getVideoUri("video") +"')," +
                "(654, 'Class 12', 'Physics', '8 ELECTROMAGNETIC WAVES', '8.1ELECTROMAGNETIC WAVES', 'DISPLACEMENT CURRENT', '10', '"+ getVideoUri("video") +"')," +
                "(655, 'Class 12', 'Physics', '8 ELECTROMAGNETIC WAVES', '8.1ELECTROMAGNETIC WAVES', 'MAXWELLS EQUATION', '10', '"+ getVideoUri("video") +"')," +
                "(656, 'Class 12', 'Physics', '8 ELECTROMAGNETIC WAVES', '8.1ELECTROMAGNETIC WAVES', 'ENERGY OF ELECTROMAGNETIC WAVES', '10', '"+ getVideoUri("video") +"')," +
                "(657, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.1 PLANE MIRROR', 'INTRODUCTION', '8', '"+ getVideoUri("video") +"')," +
                "(658, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.1 PLANE MIRROR', 'IMAGE AND OBJECT', '8', '"+ getVideoUri("video") +"')," +
                "(659, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.2 SPHERICAL MIRROR', 'INTRDUCTION', '10', '"+ getVideoUri("video") +"')," +
                "(660, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.2 SPHERICAL MIRROR', 'BASIC CONCEPTS OF SPHERICAL MIRROR', '10', '"+ getVideoUri("video") +"')," +
                "(661, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.2 SPHERICAL MIRROR', 'RAY TRACING', '10', '"+ getVideoUri("video") +"')," +
                "(662, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.2 SPHERICAL MIRROR', 'SIGN CONVENTION', '10', '"+ getVideoUri("video") +"')," +
                "(663, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.2 SPHERICAL MIRROR', 'MIRROR FORMULA', '10', '"+ getVideoUri("video") +"')," +
                "(664, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.3 SPHERICAL MIRROR AND LAWS OF REFRACTION', 'LAWS OF REFRACTION', '7', '"+ getVideoUri("video") +"')," +
                "(665, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.4 REFRACTION-1', 'REAL AND APPARENT DEPTH', '8', '"+ getVideoUri("video") +"')," +
                "(666, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.4 REFRACTION-2', 'MULTIPLE MEDIA', '9', '"+ getVideoUri("video") +"')," +
                "(667, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.4 REFRACTION-3', 'GLASS SLAB', '10', '"+ getVideoUri("video") +"')," +
                "(668, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.5 REFRACTION-||', 'REAL AND APPARENT DEPTH', '10', '"+ getVideoUri("video") +"')," +
                "(669, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.5 REFRACTION-||', 'REFRACTION IN PARALLEL FACES', '10', '"+ getVideoUri("video") +"')," +
                "(670, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.5 REFRACTION-||', 'TOTAL INTERNAL REFLECTION', '10', '"+ getVideoUri("video") +"')," +
                "(671, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.6 PRISM', 'PRISM-INTRODUCTION', '10', '"+ getVideoUri("video") +"')," +
                "(672, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.6 PRISM', 'CONDITION FOR NO EMERGENCE', '10', '"+ getVideoUri("video") +"')," +
                "(673, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.6 PRISM', 'DEVIATION', '10', '"+ getVideoUri("video") +"')," +
                "(674, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.6 PRISM', 'DISPERSION', '10', '"+ getVideoUri("video") +"')," +
                "(675, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.7 REFRACTION THROUGH CURVED SURFACE', 'REFRACTION THROUGH CURVED SURFACE', '10', '"+ getVideoUri("video") +"')," +
                "(676, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.7 REFRACTION THROUGH CURVED SURFACE', 'LENS', '10', '"+ getVideoUri("video") +"')," +
                "(677, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.7 REFRACTION THROUGH CURVED SURFACE', 'DISCUSSION ON LENS MAKER FORMULA', '10', '"+ getVideoUri("video") +"')," +
                "(678, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.8 LENS', 'LENS FORMULA', '10', '"+ getVideoUri("video") +"')," +
                "(679, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.8 LENS', 'DISPLACEMENT METHOD', '10', '"+ getVideoUri("video") +"')," +
                "(680, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.8 LENS', 'LENS COMBINATION', '10', '"+ getVideoUri("video") +"')," +
                "(681, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.9 OPTICAL INSTRUMENTS', 'DEFECT OF VISION', '10', '"+ getVideoUri("video") +"')," +
                "(682, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.9 OPTICAL INSTRUMENTS', 'SIMPLE MICROSCOPE', '10', '"+ getVideoUri("video") +"')," +
                "(683, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.9 OPTICAL INSTRUMENTS', 'COMPOUND MIRCROSCOPE', '10', '"+ getVideoUri("video") +"')," +
                "(684, 'Class 12', 'Physics', '9 RAY OPTICS AND OPTICAL INSTRUMENTS', '9.9 OPTICAL INSTRUMENTS', 'TELESCOPE AND EYE', '10', '"+ getVideoUri("video") +"')," +
                "(685, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.1WAVE OPTICS', ' HISTORICAL NOTE', '10', '"+ getVideoUri("video") +"')," +
                "(686, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.1WAVE OPTICS', 'WAVE FIRST', '10', '"+ getVideoUri("video") +"')," +
                "(687, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.1WAVE OPTICS', 'REFLECTION AND REFRACTION', '10', '"+ getVideoUri("video") +"')," +
                "(688, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.1WAVE OPTICS', 'INTERFERENCE', '10', '"+ getVideoUri("video") +"')," +
                "(689, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.2INTERFERENCE', 'GEOMETRICAL BASIC', '10', '"+ getVideoUri("video") +"')," +
                "(690, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.2INTERFERENCE', 'YDSE', '10', '"+ getVideoUri("video") +"')," +
                "(691, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.2INTERFERENCE', 'LOCATION OF FRINGES', '10', '"+ getVideoUri("video") +"')," +
                "(692, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.3 DIFFRACTION AND POLARISATION', ' DIFFRACTION', '10', '"+ getVideoUri("video") +"')," +
                "(693, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.3 DIFFRACTION AND POLARISATION', ' TYPES OF DIFFRACTION', '10', '"+ getVideoUri("video") +"')," +
                "(694, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.3 DIFFRACTION AND POLARISATION', ' RAYLEIGH CRITERIA', '10', '"+ getVideoUri("video") +"')," +
                "(695, 'Class 12', 'Physics', '10 WAVE OPTICS', '10.3 DIFFRACTION AND POLARISATION', ' POLARISATION', '10', '"+ getVideoUri("video") +"')," +
                "(696, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.1 PHOTOELECTRIC EFFECT', 'INTRODUCTION TO MODERN PHYSICS', '10', '"+ getVideoUri("video") +"')," +
                "(697, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.1 PHOTOELECTRIC EFFECT', ' DISCOVERY OF ELECTRONS', '10', '"+ getVideoUri("video") +"')," +
                "(698, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.1 PHOTOELECTRIC EFFECT', 'PHOTOELECTRIC EFFECT', '10', '"+ getVideoUri("video") +"')," +
                "(699, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.1 PHOTOELECTRIC EFFECT', ' EINSTEINS PHOTON THEORY', '10', '"+ getVideoUri("video") +"')," +
                "(700, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.1 PHOTOELECTRIC EFFECT', ' PROPERTIES OF PHOTONS', '10', '"+ getVideoUri("video") +"')," +
                "(701, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.2 PHOTOELECTRIC EFFECT(CONTD..)', ' EXAMPLES BASED ON PHOTOELECTRIC EFFECT', '8', '"+ getVideoUri("video") +"')," +
                "(702, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.2 PHOTOELECTRIC EFFECT(CONTD..)', 'RADIATION PRESSURE', '8', '"+ getVideoUri("video") +"')," +
                "(703, 'Class 12', 'Physics', '11 DUAL NATURE OF RADIATION AND MATTER', '11.2 PHOTOELECTRIC EFFECT(CONTD..)', ' DE BROGLIE WAVES', '8', '"+ getVideoUri("video") +"')," +
                "(704, 'Class 12', 'Physics', '12 ATOMS', '12.1 ATOMIC STRUCTURE AND BOHR MODEL', ' EARLY ATOMIC MODELS', '10', '"+ getVideoUri("video") +"')," +
                "(705, 'Class 12', 'Physics', '12 ATOMS', '12.1 ATOMIC STRUCTURE AND BOHR MODEL', ' BOHR MODEL FOR HYDROGEN ATOM', '8', '"+ getVideoUri("video") +"')," +
                "(706, 'Class 12', 'Physics', '12 ATOMS', '12.1 ATOMIC STRUCTURE AND BOHR MODEL', ' ENERGY LEVELS AND TRANSITION OF ELECTRONS', '15', '"+ getVideoUri("video") +"')," +
                "(707, 'Class 12', 'Physics', '12 ATOMS', '12.1 ATOMIC STRUCTURE AND BOHR MODEL', 'HYDROGEN LIKE ATOMS', '10', '"+ getVideoUri("video") +"')," +
                "(708, 'Class 12', 'Physics', '12 ATOMS', '12.2 BOHR MODEL AND TRANSITION OF ELECTRONS', ' SOLVED EXAMPLES BASED ON H-LIKE ATOMS', '10', '"+ getVideoUri("video") +"')," +
                "(709, 'Class 12', 'Physics', '12 ATOMS', '12.2 BOHR MODEL AND TRANSITION OF ELECTRONS', ' EXCITATION THROUGH COLLISIONS', '10', '"+ getVideoUri("video") +"')," +
                "(710, 'Class 12', 'Physics', '12 ATOMS', '12.2 BOHR MODEL AND TRANSITION OF ELECTRONS', ' WAVE MODEL OF H-ATOM', '12', '"+ getVideoUri("video") +"');";

        db.execSQL(sql2);

        String sql3 = "INSERT INTO `topic` (`topic_id`, `class`, `subject`, `chapter`, `topic_name`, `sub_topic_name`, `topic_price`, `topic_video_url`) VALUES" +
                "(711, 'Class 12', 'Physics', '12 ATOMS', '12.3 X- RAYS', ' DISCOVERY AND PRODUCTION OF X-RAYS', '10', '"+ getVideoUri("video") +"')," +
                "(712, 'Class 12', 'Physics', '12 ATOMS', '12.3 X- RAYS', ' CHARACTERISTIC SPECTRUM', '10', '"+ getVideoUri("video") +"')," +
                "(713, 'Class 12', 'Physics', '12 ATOMS', '12.3 X- RAYS', ' EXPLANATION OF MOSELEYS LAW', '12', '"+ getVideoUri("video") +"')," +
                "(714, 'Class 12', 'Physics', '12 ATOMS', '12.3 X- RAYS', ' ABSORPTION OF X-RAYS', '10', '"+ getVideoUri("video") +"')," +
                "(715, 'Class 12', 'Physics', '13 NUCLEI', '13.1 NUCLEUS AND ITS STABILITY', ' INTRODUCTION TO NUCLEUS', '10', '"+ getVideoUri("video") +"')," +
                "(716, 'Class 12', 'Physics', '13 NUCLEI', '13.1 NUCLEUS AND ITS STABILITY', ' STABILITY OF NUCLEUS', '15', '"+ getVideoUri("video") +"')," +
                "(717, 'Class 12', 'Physics', '13 NUCLEI', '13.1 NUCLEUS AND ITS STABILITY', ' BINDING ENERGY', '15', '"+ getVideoUri("video") +"')," +
                "(718, 'Class 12', 'Physics', '13 NUCLEI', '13.1 NUCLEUS AND ITS STABILITY', ' UNSTABILITY OF NUCLEUS', '12', '"+ getVideoUri("video") +"')," +
                "(719, 'Class 12', 'Physics', '13 NUCLEI', '13.1 NUCLEUS AND ITS STABILITY', ' DECAY PROCESSES', '8', '"+ getVideoUri("video") +"')," +
                "(720, 'Class 12', 'Physics', '13 NUCLEI', '13.2 RADIOACTIVITY AND NUCLEAR REACTIONS', ' LAW OF RADIOACTIVE DECAY', '10', '"+ getVideoUri("video") +"')," +
                "(721, 'Class 12', 'Physics', '13 NUCLEI', '13.2 RADIOACTIVITY AND NUCLEAR REACTIONS', ' ACTIVITY OF A SAMPLE', '15', '"+ getVideoUri("video") +"')," +
                "(722, 'Class 12', 'Physics', '13 NUCLEI', '13.2 RADIOACTIVITY AND NUCLEAR REACTIONS', ' SERIES AND PARALLEL DECAY', '10', '"+ getVideoUri("video") +"')," +
                "(723, 'Class 12', 'Physics', '13 NUCLEI', '13.2 RADIOACTIVITY AND NUCLEAR REACTIONS', ' NUCLEAR REACTIONS', '10', '"+ getVideoUri("video") +"')," +
                "(724, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.1 SEMICONDUCTORS', ' ELECTRICAL PROPERTIES OF SEMICONDUCTORS', '10', '"+ getVideoUri("video") +"')," +
                "(725, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.1 SEMICONDUCTORS', ' ENERGY BOND DIAGRAM OF INSULATORS AND SEMICONDUCTORS', '15', '"+ getVideoUri("video") +"')," +
                "(726, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.1 SEMICONDUCTORS', ' ENERGY BOND DIAGRAM AND CONCEPT OF HOLES', '12', '"+ getVideoUri("video") +"')," +
                "(727, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.1 SEMICONDUCTORS', ' CURRENTS IN SEMICONDUCTORS', '10', '"+ getVideoUri("video") +"')," +
                "(728, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.1 SEMICONDUCTORS', ' EXTRINSIC SEMICONDUCTORS', '10', '"+ getVideoUri("video") +"')," +
                "(729, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.2 P-N FUNCTION DIODES', ' P-N FUNCTION DIODES', '10', '"+ getVideoUri("video") +"')," +
                "(730, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.2 P-N FUNCTION DIODES', ' RECTIFICATION AND VOLTAGE STABILIZATION', '12', '"+ getVideoUri("video") +"')," +
                "(731, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.2 P-N FUNCTION DIODES', ' OTHER DIODES', '12', '"+ getVideoUri("video") +"')," +
                "(732, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.3 TRANSISTORS', ' INTRODUCTION TO TRANSITORS', '10', '"+ getVideoUri("video") +"')," +
                "(733, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.3 TRANSISTORS', ' COMMON EMITTER CONFIGURATION', '10', '"+ getVideoUri("video") +"')," +
                "(734, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.3 TRANSISTORS', ' USES OF TRANSISTOR', '12', '"+ getVideoUri("video") +"')," +
                "(735, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.4 LOGIC GATES', ' DIGITAL AND ANALOG SIGNALS', '12', '"+ getVideoUri("video") +"')," +
                "(736, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.4 LOGIC GATES', ' BOOLEAN OPERATORS', '12', '"+ getVideoUri("video") +"')," +
                "(737, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.4 LOGIC GATES', ' COMBINATION OF GATES', '10', '"+ getVideoUri("video") +"')," +
                "(738, 'Class 12', 'Physics', '14 SEMICONDUCTOR ELECTRONICS :MATERIALS,DEVICES AND SIMPLE CIRCUITS', '14.4 LOGIC GATES', ' BOOLEAN ALGEBRA', '10', '"+ getVideoUri("video") +"')," +
                "(739, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.1 PRINCIPLES OF COMMUNICATION', ' BASICS OF COMMUNICATION', '10', '"+ getVideoUri("video") +"')," +
                "(740, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.1 PRINCIPLES OF COMMUNICATION', ' AMPITUDE MODULATION', '10', '"+ getVideoUri("video") +"')," +
                "(741, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.1 PRINCIPLES OF COMMUNICATION', ' FREQUENCY MODULATION', '10', '"+ getVideoUri("video") +"')," +
                "(742, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.1 PRINCIPLES OF COMMUNICATION', ' CHANNELS OF COMMUNICATION', '10', '"+ getVideoUri("video") +"')," +
                "(743, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.2 APPLICATION OF COMMUNICATION SYSTEM', ' INTERNET', '15', '"+ getVideoUri("video") +"')," +
                "(744, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.2 APPLICATION OF COMMUNICATION SYSTEM', ' MOBILE', '15', '"+ getVideoUri("video") +"')," +
                "(745, 'Class 12', 'Physics', '15 COMMUNICATION SYSTEM', '15.2 APPLICATION OF COMMUNICATION SYSTEM', ' GPS', '5', '"+ getVideoUri("video") +"')," +
                "(746, 'Class 12', 'Maths', '1. MATRICES', '1.1 Types and Algebra of Matrices', ' Introduction ordre of Matrix', '15', '"+ getVideoUri("video") +"')," +
                "(747, 'Class 12', 'Maths', '1. MATRICES', '1.1 Types and Algebra of Matrices', ' Type of Matrices', '15', '"+ getVideoUri("video") +"')," +
                "(748, 'Class 12', 'Maths', '1. MATRICES', '1.1 Types and Algebra of Matrices', ' Algebra of Matrices', '10', '"+ getVideoUri("video") +"')," +
                "(749, 'Class 12', 'Maths', '1. MATRICES', '1.2 Matrix multiplication', ' Matrix Multiplication', '10', '"+ getVideoUri("video") +"')," +
                "(750, 'Class 12', 'Maths', '1. MATRICES', '1.2 Matrix multiplication', ' Types of Matrices Based on Matrix Multiplication', '10', '"+ getVideoUri("video") +"')," +
                "(751, 'Class 12', 'Maths', '1. MATRICES', '1.3 Transpose and Inverse', ' Transpose', '15', '"+ getVideoUri("video") +"')," +
                "(752, 'Class 12', 'Maths', '1. MATRICES', '1.3 Transpose and Inverse', ' Orthogonal Matrix', '20', '"+ getVideoUri("video") +"')," +
                "(753, 'Class 12', 'Maths', '1. MATRICES', '1.3 Transpose and Inverse', ' Complex Conjugate', '20', '"+ getVideoUri("video") +"')," +
                "(754, 'Class 12', 'Maths', '1. MATRICES', '1.3 Transpose and Inverse', ' Inverse of a Matix', '15', '"+ getVideoUri("video") +"')," +
                "(755, 'Class 12', 'Maths', '1. MATRICES', '1.3 Transpose and Inverse', ' Inverse by Elementry Row Transformation', '15', '"+ getVideoUri("video") +"')," +
                "(756, 'Class 12', 'Maths', '2. DETERMINANTS', '2.1 Determinant and its Properties', ' Introduction', '15', '"+ getVideoUri("video") +"')," +
                "(757, 'Class 12', 'Maths', '2. DETERMINANTS', '2.1 Determinant and its Properties', ' Properties of Determinants', '15', '"+ getVideoUri("video") +"')," +
                "(758, 'Class 12', 'Maths', '2. DETERMINANTS', '2.2 Properties of Determinants', ' Circular and Examples', '20', '"+ getVideoUri("video") +"')," +
                "(759, 'Class 12', 'Maths', '2. DETERMINANTS', '2.2 Properties of Determinants', ' Differentiation of a Determinant', '20', '"+ getVideoUri("video") +"')," +
                "(760, 'Class 12', 'Maths', '2. DETERMINANTS', '2.2 Properties of Determinants', ' Integration,Summation and Limit', '20', '"+ getVideoUri("video") +"')," +
                "(761, 'Class 12', 'Maths', '2. DETERMINANTS', '2.3 Adjoint of  a Matrix,System of Equations', ' Adjoint of a Matrix', '12', '"+ getVideoUri("video") +"')," +
                "(762, 'Class 12', 'Maths', '2. DETERMINANTS', '2.3 Adjoint of  a Matrix,System of Equations', ' Properties of Adjoint', '10', '"+ getVideoUri("video") +"')," +
                "(763, 'Class 12', 'Maths', '2. DETERMINANTS', '2.3 Adjoint of  a Matrix,System of Equations', ' Non-homogeneous equation', '16', '"+ getVideoUri("video") +"')," +
                "(764, 'Class 12', 'Maths', '2. DETERMINANTS', '2.3 Adjoint of  a Matrix,System of Equations', ' Homogeneous equation', '25', '"+ getVideoUri("video") +"')," +
                "(765, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.1 Introduction and Inverse of Basic Trignometric Functions', ' Invertible Function', '15', '"+ getVideoUri("video") +"')," +
                "(766, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.1 Introduction and Inverse of Basic Trignometric Functions', ' Principal Value and Inverse of Basic Functions', '20', '"+ getVideoUri("video") +"')," +
                "(767, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.2 Properties of Inverse', ' Properties of Inverse', '20', '"+ getVideoUri("video") +"')," +
                "(768, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.2 Properties of Inverse', ' Inverse Identities', '15', '"+ getVideoUri("video") +"')," +
                "(769, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.3 Converse of One inverse into Inverse', ' Conversion', '15', '"+ getVideoUri("video") +"')," +
                "(770, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.4 Sum or Difference of Inverse function', ' Sum of Tangent Inverse Function', '20', '"+ getVideoUri("video") +"')," +
                "(771, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.4 Sum or Difference of Inverse function', ' Sum of Cosine Function', '15', '"+ getVideoUri("video") +"')," +
                "(772, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.4 Sum or Difference of Inverse function', ' Sum of Sine Function', '20', '"+ getVideoUri("video") +"')," +
                "(773, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.5 Inverse of Multiple Angle', ' Inverse of Multiple Angle(Sine and Cosine Function)', '30', '"+ getVideoUri("video") +"')," +
                "(774, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.5 Inverse of Multiple Angle', ' Inverse of Multiple Angle(Tangent Function)', '20', '"+ getVideoUri("video") +"')," +
                "(775, 'Class 12', 'Maths', '3. INVERSE TRIGNOMETRIC FUNCTIONS', '3.5 Inverse of Multiple Angle', ' Inverse of One Multiple into Different Inverse Function', '20', '"+ getVideoUri("video") +"')," +
                "(776, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.1 Relation,Definition Types', ' Cartesian,Product,Relation', '15', '"+ getVideoUri("video") +"')," +
                "(777, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.1 Relation,Definition Types', ' Types of Relations', '10', '"+ getVideoUri("video") +"')," +
                "(778, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.1 Relation,Definition Types', ' Transitive,Equivalence Relation', '16', '"+ getVideoUri("video") +"')," +
                "(779, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.2 Function-Domain and Range', ' Function', '12', '"+ getVideoUri("video") +"')," +
                "(780, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.2 Function-Domain and Range', ' Log function', '15', '"+ getVideoUri("video") +"')," +
                "(781, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.2 Function-Domain and Range', ' Greatest Integer and Modulus Function', '20', '"+ getVideoUri("video") +"')," +
                "(782, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.2 Function-Domain and Range', ' Range', '20', '"+ getVideoUri("video") +"')," +
                "(783, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.3 Range,Types of the Function', ' Range', '20', '"+ getVideoUri("video") +"')," +
                "(784, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.3 Range,Types of the Function', ' Types of Functions', '12', '"+ getVideoUri("video") +"')," +
                "(785, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.3 Range,Types of the Function', ' Monotoxicity', '20', '"+ getVideoUri("video") +"')," +
                "(786, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.4 Composite,Inverse,Even,Odd Functions', ' Composite Function', '20', '"+ getVideoUri("video") +"')," +
                "(787, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.4 Composite,Inverse,Even,Odd Functions', ' Inverse Function', '20', '"+ getVideoUri("video") +"')," +
                "(788, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.5 Periodic Functions ', ' Periodic Functions ', '20', '"+ getVideoUri("video") +"')," +
                "(789, 'Class 12', 'Maths', '4. RELATIONS AND FUNCTIONS', '4.6 Binary Operations', ' Binary', '15', '"+ getVideoUri("video") +"')," +
                "(790, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.1 Limit', ' Definition,Standard Results', '15', '"+ getVideoUri("video") +"')," +
                "(791, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.1 Limit', ' Series Expansion', '10', '"+ getVideoUri("video") +"')," +
                "(792, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.1 Limit', ' D-LHospital Rule', '10', '"+ getVideoUri("video") +"')," +
                "(793, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.2 Limit,Continuity and Differentiability', ' Limit-1 Form', '15', '"+ getVideoUri("video") +"')," +
                "(794, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.2 Limit,Continuity and Differentiability', ' Sandwich Theorm,Continuity', '15', '"+ getVideoUri("video") +"')," +
                "(795, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.2 Limit,Continuity and Differentiability', ' Differentiability', '15', '"+ getVideoUri("video") +"')," +
                "(796, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Differentiation of Inverse Trignometric Function', '10', '"+ getVideoUri("video") +"')," +
                "(797, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Logarithmic Differentiation', '15', '"+ getVideoUri("video") +"')," +
                "(798, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Parametric Form', '10', '"+ getVideoUri("video") +"')," +
                "(799, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Seconod Order Derivatives', '15', '"+ getVideoUri("video") +"')," +
                "(800, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Differentiability', '13', '"+ getVideoUri("video") +"')," +
                "(801, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Rolles Theorm', '20', '"+ getVideoUri("video") +"')," +
                "(802, 'Class 12', 'Maths', '5. CONTINUITY AND DIFFERENTIABILITY', '5.3 Differentiation', ' Mean Value Theorm', '15', '"+ getVideoUri("video") +"')," +
                "(803, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.1 Rate Measurer,Tangent,Normal', ' Derivatives Rate Measurer', '10', '"+ getVideoUri("video") +"')," +
                "(804, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.1 Rate Measurer,Tangent,Normal', ' Tangent and Normal', '15', '"+ getVideoUri("video") +"')," +
                "(805, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.2 Approximation,Increasing and Decreasing Function', ' Angle between Two Curves', '15', '"+ getVideoUri("video") +"')," +
                "(806, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.2 Approximation,Increasing and Decreasing Function', ' Increasing and Decreasing Function', '15', '"+ getVideoUri("video") +"')," +
                "(807, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.3 Increasing-Decreasing Maxima-Minima', ' Increasing-Decreasing function', '10', '"+ getVideoUri("video") +"')," +
                "(808, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.3 Increasing-Decreasing Maxima-Minima', ' Inequalities', '20', '"+ getVideoUri("video") +"')," +
                "(809, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.3 Increasing-Decreasing Maxima-Minima', ' Maxima-Minima', '20', '"+ getVideoUri("video") +"')," +
                "(810, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.3 Increasing-Decreasing Maxima-Minima', ' Higher Order Derivative and First Order Derivative Test', '15', '"+ getVideoUri("video") +"')," +
                "(811, 'Class 12', 'Maths', '6. APPLICATION OF DERIVATIVES', '6.3 Increasing-Decreasing Maxima-Minima', ' Examples on Maxima-Minima', '16', '"+ getVideoUri("video") +"')," +
                "(812, 'Class 12', 'Maths', '7. INTEGRALS', '7.1 Indefinite Integrals', ' Some Standard Results and Methods of Integration', '10', '"+ getVideoUri("video") +"')," +
                "(813, 'Class 12', 'Maths', '7. INTEGRALS', '7.2 Rational and Irrational Functions', ' Integration of Trignometric Functions', '12', '"+ getVideoUri("video") +"')," +
                "(814, 'Class 12', 'Maths', '7. INTEGRALS', '7.2 Rational and Irrational Functions', ' Integration of Rational,Irrational Functions', '15', '"+ getVideoUri("video") +"')," +
                "(815, 'Class 12', 'Maths', '7. INTEGRALS', '7.3 Partial Fraction,Integration by Parts', ' Partial Fraction', '10', '"+ getVideoUri("video") +"')," +
                "(816, 'Class 12', 'Maths', '7. INTEGRALS', '7.3 Partial Fraction,Integration by Parts', ' Example on Partial Fraction', '10', '"+ getVideoUri("video") +"')," +
                "(817, 'Class 12', 'Maths', '7. INTEGRALS', '7.3 Partial Fraction,Integration by Parts', ' Methods of Suppression', '10', '"+ getVideoUri("video") +"')," +
                "(818, 'Class 12', 'Maths', '7. INTEGRALS', '7.3 Partial Fraction,Integration by Parts', ' Integration by Parts', '10', '"+ getVideoUri("video") +"')," +
                "(819, 'Class 12', 'Maths', '7. INTEGRALS', '7.4 Indefinite Integrals', ' Integration by Parts', '12', '"+ getVideoUri("video") +"')," +
                "(820, 'Class 12', 'Maths', '7. INTEGRALS', '7.4 Indefinite Integrals', ' Special integrals', '12', '"+ getVideoUri("video") +"')," +
                "(821, 'Class 12', 'Maths', '7. INTEGRALS', '7.4 Indefinite Integrals', ' Reduction Formulae', '10', '"+ getVideoUri("video") +"')," +
                "(822, 'Class 12', 'Maths', '7. INTEGRALS', '7.5 Properties of Definite Integral', ' Definition', '10', '"+ getVideoUri("video") +"')," +
                "(823, 'Class 12', 'Maths', '7. INTEGRALS', '7.5 Properties of Definite Integral', ' Properties of Definite Integral', '10', '"+ getVideoUri("video") +"')," +
                "(824, 'Class 12', 'Maths', '7. INTEGRALS', '7.5 Properties of Definite Integral', ' Properties Related to Periodic Function', '10', '"+ getVideoUri("video") +"')," +
                "(825, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.1 Catrtesian Form and Algebra', 'Introduction to Iota', '10', '"+ getVideoUri("video") +"')," +
                "(826, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.1 Catrtesian Form and Algebra', 'X+iY,Representation,Addition,Modulus', '20', '"+ getVideoUri("video") +"')," +
                "(827, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.1 Catrtesian Form and Algebra', 'Conjugate,Multiplication', '15', '"+ getVideoUri("video") +"')," +
                "(828, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.1 Catrtesian Form and Algebra', 'Examples and Multilplicative Inverse', '12', '"+ getVideoUri("video") +"')," +
                "(829, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.2 Polar Form,Square Root,Properties of Modulus', 'Polor Form', '12', '"+ getVideoUri("video") +"')," +
                "(830, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.2 Polar Form,Square Root,Properties of Modulus', 'Multiplication,Division Using Polar', '10', '"+ getVideoUri("video") +"')," +
                "(831, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.2 Polar Form,Square Root,Properties of Modulus', 'Square Root,Properties of Modulus', '15', '"+ getVideoUri("video") +"')," +
                "(832, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.3 Eulers Form ,De-Moivres Theorem Cube Roots,nth Roots', 'Properties of Modulus', '12', '"+ getVideoUri("video") +"')," +
                "(833, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.3 Eulers Form ,De-Moivres Theorem Cube Roots,nth Roots', 'Eulers Form,De-Moivres Theorem', '15', '"+ getVideoUri("video") +"')," +
                "(834, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.3 Eulers Form ,De-Moivres Theorem Cube Roots,nth Roots', 'Cube Roots of Unity', '14', '"+ getVideoUri("video") +"')," +
                "(835, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.3 Eulers Form ,De-Moivres Theorem Cube Roots,nth Roots', 'nth Roots of Unity', '12', '"+ getVideoUri("video") +"')," +
                "(836, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.4 Rotation,Geometry Introduction', 'Rotation of Complex Numbers', '10', '"+ getVideoUri("video") +"')," +
                "(837, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.5 Geometry with Complex Numbers', 'Equation of a Straight Line', '10', '"+ getVideoUri("video") +"')," +
                "(838, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.5 Geometry with Complex Numbers', 'Circle', '15', '"+ getVideoUri("video") +"')," +
                "(839, 'Class 11', 'Maths', '1. COMPLEX NUMBERS & QUADRATIC EQUATIONS', '1.5 Geometry with Complex Numbers', 'Ray,Section of a Circle', '14', '"+ getVideoUri("video") +"')," +
                "(840, 'Class 11', 'Maths', '2. PRINCIPLE OF MATHEMATICAL INDUCTION', '2.1 Principle of Mathematical Induction', 'Principle of Mathematical Induction', '10', '"+ getVideoUri("video") +"')," +
                "(841, 'Class 11', 'Maths', '2. PRINCIPLE OF MATHEMATICAL INDUCTION', '2.1 Principle of Mathematical Induction', 'Examples of PMI', '10', '"+ getVideoUri("video") +"')," +
                "(842, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.1 Introduction,General Terms and its Application', 'Introduction & Pascals Triangle', '10', '"+ getVideoUri("video") +"')," +
                "(843, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.1 Introduction,General Terms and its Application', 'General Terms and its Applications', '15', '"+ getVideoUri("video") +"')," +
                "(844, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.1 Introduction,General Terms and its Application', 'Coefficient of xr', '15', '"+ getVideoUri("video") +"')," +
                "(845, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.2 Rational Terms,Greatest Terms and Multinominal Theorm', 'Number of Rational Term', '10', '"+ getVideoUri("video") +"')," +
                "(846, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.2 Rational Terms,Greatest Terms and Multinominal Theorm', 'Greatest Bionomial Coefficient', '12', '"+ getVideoUri("video") +"')," +
                "(847, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.2 Rational Terms,Greatest Terms and Multinominal Theorm', 'Greatest Term', '15', '"+ getVideoUri("video") +"')," +
                "(848, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.2 Rational Terms,Greatest Terms and Multinominal Theorm', 'Multinomial Term', '20', '"+ getVideoUri("video") +"')," +
                "(849, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.3 Binomial Series', 'Sum of Coefficients', '20', '"+ getVideoUri("video") +"')," +
                "(850, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.3 Binomial Series', 'Different Binomial Series', '15', '"+ getVideoUri("video") +"')," +
                "(851, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.3 Binomial Series', 'Binomial Series Using Differentiation', '20', '"+ getVideoUri("video") +"')," +
                "(852, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.3 Binomial Series', 'Binomial Series Using Integration', '10', '"+ getVideoUri("video") +"')," +
                "(853, 'Class 11', 'Maths', '3. BIONOMIAL THEORM', '3.3 Binomial Series', 'Concept of Divisibility', '8', '"+ getVideoUri("video") +"')," +
                "(854, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.1 Introduction', 'Fundamental,Formulae', '10', '"+ getVideoUri("video") +"')," +
                "(855, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.1 Introduction', 'Properties of nCr', '20', '"+ getVideoUri("video") +"')," +
                "(856, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.1 Introduction', 'Fundamental Principle of Counting', '15', '"+ getVideoUri("video") +"')," +
                "(857, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.2 Permutation', 'Row Arrangement', '10', '"+ getVideoUri("video") +"')," +
                "(858, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.2 Permutation', 'Arrangement of Digits', '15', '"+ getVideoUri("video") +"')," +
                "(859, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.3 Rank and Circular Arrangement', 'Rank in Dictionary', '20', '"+ getVideoUri("video") +"')," +
                "(860, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.3 Rank and Circular Arrangement', 'Order', '15', '"+ getVideoUri("video") +"')," +
                "(861, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.3 Rank and Circular Arrangement', 'Circular Permutation', '16', '"+ getVideoUri("video") +"')," +
                "(862, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.4 Combination', 'Selection using nCr', '15', '"+ getVideoUri("video") +"')," +
                "(863, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.4 Combination', 'Restricted Selection', '15', '"+ getVideoUri("video") +"')," +
                "(864, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.4 Combination', 'Selection of Similar Objects', '12', '"+ getVideoUri("video") +"')," +
                "(865, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.5 Selection and Arrangement', 'Having of nPr', '20', '"+ getVideoUri("video") +"')," +
                "(866, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.5 Selection and Arrangement', 'Selection and Arrangement Using nCr', '15', '"+ getVideoUri("video") +"')," +
                "(867, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.5 Selection and Arrangement', 'Permutation with Repetition', '14', '"+ getVideoUri("video") +"')," +
                "(868, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.6 Division and Distribution', 'Division of Distinct Items', '8', '"+ getVideoUri("video") +"')," +
                "(869, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.6 Division and Distribution', 'Distribution of Total Distribution', '5', '"+ getVideoUri("video") +"')," +
                "(870, 'Class 11', 'Maths', '4. PERMUTATIONS & COMBINATIONS', '4.6 Division and Distribution', 'Application of Division and Distribution', '12', '"+ getVideoUri("video") +"')," +
                "(871, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.1 Arithmetic Progression', 'Introduction and Different Types of Sequence', '10', '"+ getVideoUri("video") +"')," +
                "(872, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.1 Arithmetic Progression', 'Arithmetic Progression', '15', '"+ getVideoUri("video") +"')," +
                "(873, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.2 Geometric Progression', 'General Term', '12', '"+ getVideoUri("video") +"')," +
                "(874, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.2 Geometric Progression', 'Sum of GP', '13', '"+ getVideoUri("video") +"')," +
                "(875, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.2 Geometric Progression', 'Infinite GP', '14', '"+ getVideoUri("video") +"')," +
                "(876, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.2 Geometric Progression', 'Properties of GP', '15', '"+ getVideoUri("video") +"')," +
                "(877, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.3 AGP,HP and Means', 'Arithmetic Geometric Progression', '10', '"+ getVideoUri("video") +"')," +
                "(878, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.3 AGP,HP and Means', 'Harmonic Progression', '8', '"+ getVideoUri("video") +"')," +
                "(879, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.3 AGP,HP and Means', 'Arithmetic,Geometric and Harmonic Mean', '10', '"+ getVideoUri("video") +"')," +
                "(880, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.4 AM>GM>HM', 'Understanding Inequality', '10', '"+ getVideoUri("video") +"')," +
                "(881, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.4 AM>GM>HM', 'Examples on inequalities', '12', '"+ getVideoUri("video") +"')," +
                "(882, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.4 AM>GM>HM', 'Important Results on AM', '8', '"+ getVideoUri("video") +"')," +
                "(883, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.5 Special Series', 'Sigma Notion and Special Series', '12', '"+ getVideoUri("video") +"')," +
                "(884, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.5 Special Series', 'Examples on Special Series', '15', '"+ getVideoUri("video") +"')," +
                "(885, 'Class 11', 'Maths', '5. SEQUENCES & SERIES', '5.5 Special Series', 'Successive Difference', '15', '"+ getVideoUri("video") +"')," +
                "(886, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.1 Measuring Angles and Allied Angles', 'Measurement of Angles', '10', '"+ getVideoUri("video") +"')," +
                "(887, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.1 Measuring Angles and Allied Angles', 'Conversion from Degree to Radians and Radians to Degree', '15', '"+ getVideoUri("video") +"')," +
                "(888, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.1 Measuring Angles and Allied Angles', 'Allied Angles,Sign Convention', '12', '"+ getVideoUri("video") +"')," +
                "(889, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.1 Measuring Angles and Allied Angles', 'Value of Trigonometric Ratios for Different Angles', '20', '"+ getVideoUri("video") +"')," +
                "(890, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.2 Identities,Domain,Range and Graph', 'Trigonometric Identities', '15', '"+ getVideoUri("video") +"')," +
                "(891, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.2 Identities,Domain,Range and Graph', 'Domain,Range and Graph', '12', '"+ getVideoUri("video") +"')," +
                "(892, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.3 Periods of Trignometric Functions', 'Peroid of Trigonometric Functions', '15', '"+ getVideoUri("video") +"')," +
                "(893, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.3 Periods of Trignometric Functions', 'Examples of Periodic Functions', '12', '"+ getVideoUri("video") +"')," +
                "(894, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.4 Elimination Method', 'Elimination Method', '8', '"+ getVideoUri("video") +"')," +
                "(895, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.4 Elimination Method', 'Examples of Peroid,Elimination Method,Range', '10', '"+ getVideoUri("video") +"')," +
                "(896, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.4 Elimination Method', 'AM-GM Inequaliy', '12', '"+ getVideoUri("video") +"')," +
                "(897, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.5 Sum/ Difference of Two Angles,Transformation from Product to Sum/Difference', 'sin(A+-B),cos(A+-B)),tan(A+-B)', '20', '"+ getVideoUri("video") +"')," +
                "(898, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.5 Sum/ Difference of Two Angles,Transformation from Product to Sum/Difference', 'cot(A+-B),Componendo-Dividendo Rule', '15', '"+ getVideoUri("video") +"')," +
                "(899, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.5 Sum/ Difference of Two Angles,Transformation from Product to Sum/Difference', '2 Sin A Cos B,2 cos A Sin B,2 cos A cos B,2 sin A sin B', '12', '"+ getVideoUri("video") +"')," +
                "(900, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.6 Product to Sum/Difference Form,Multiple Angles', 'Transformation From Sum/Difference to Product Form', '10', '"+ getVideoUri("video") +"')," +
                "(901, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.6 Product to Sum/Difference Form,Multiple Angles', 'Examples of Multiple Angles', '15', '"+ getVideoUri("video") +"')," +
                "(902, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.6 Product to Sum/Difference Form,Multiple Angles', 'Half Angle Results', '12', '"+ getVideoUri("video") +"')," +
                "(903, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.7 Conditional Identities,Summation of Series', 'Examples on conditional identities', '10', '"+ getVideoUri("video") +"')," +
                "(904, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.7 Conditional Identities,Summation of Series', 'sin(A+B+C),cos(A+B+C),tan(A+B+C)', '20', '"+ getVideoUri("video") +"')," +
                "(905, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.7 Conditional Identities,Summation of Series', 'sin(A+B) sin(A-B)', '12', '"+ getVideoUri("video") +"')," +
                "(906, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.8 Particular and General Solution', 'Principal Value', '10', '"+ getVideoUri("video") +"')," +
                "(907, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.8 Particular and General Solution', 'General Solution', '10', '"+ getVideoUri("video") +"')," +
                "(908, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.8 Particular and General Solution', 'Examples on Trigonometric Equations', '10', '"+ getVideoUri("video") +"')," +
                "(909, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.9 General Solution of Different Types of Equation', 'Solution of a sin x + cos b=c', '12', '"+ getVideoUri("video") +"')," +
                "(910, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.9 General Solution of Different Types of Equation', 'Solution of simulatneous equation', '20', '"+ getVideoUri("video") +"')," +
                "(911, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.9 General Solution of Different Types of Equation', 'Solution of equations using graphs', '18', '"+ getVideoUri("video") +"')," +
                "(912, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.10 Equations in Two Variables and Examples', 'Equation involving Two Variables', '10', '"+ getVideoUri("video") +"')," +
                "(913, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.11 Sine,Cosine,Tangent Rule', 'Sine rule,Cosine rule,Projection formulae', '12', '"+ getVideoUri("video") +"')," +
                "(914, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.11 Sine,Cosine,Tangent Rule', 'Examples on Sine,Cosine rule', '15', '"+ getVideoUri("video") +"')," +
                "(915, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.11 Sine,Cosine,Tangent Rule', 'Tangent Rule,m-n Theorem', '10', '"+ getVideoUri("video") +"')," +
                "(916, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.12 Half-angle Results Area', 'Half-Angle Formulae', '8', '"+ getVideoUri("video") +"')," +
                "(917, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.12 Half-angle Results Area', 'Area of triangle,Geometrial Results', '5', '"+ getVideoUri("video") +"')," +
                "(918, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.12 Half-angle Results Area', 'Inradius of Triangle', '5', '"+ getVideoUri("video") +"')," +
                "(919, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.13 Ex-radii,Cyclic Quadrilateral,Regular Polygon', 'Ex-radii of Triangle', '10', '"+ getVideoUri("video") +"')," +
                "(920, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.13 Ex-radii,Cyclic Quadrilateral,Regular Polygon', 'Cyclic Quardrilateral', '10', '"+ getVideoUri("video") +"')," +
                "(921, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.13 Ex-radii,Cyclic Quadrilateral,Regular Polygon', 'Ptolemys Theorm,Length of Median', '10', '"+ getVideoUri("video") +"')," +
                "(922, 'Class 11', 'Maths', '6. TRIGONOMETRIC FUNCTIONS', '6.14 Pedal Triangle and Different Examples', 'Pedal Triangle', '10', '"+ getVideoUri("video") +"')," +
                "(923, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.1 2D-Points', 'Distance Formula-Section Formula,Area', '10', '"+ getVideoUri("video") +"')," +
                "(924, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.1 2D-Points', 'Centres', '15', '"+ getVideoUri("video") +"')," +
                "(925, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.1 2D-Points', 'Special Points,Slope,Angle between Lines', '15', '"+ getVideoUri("video") +"')," +
                "(926, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.2Different Forms of Straight Line', 'Different Forms of Straight Line', '12', '"+ getVideoUri("video") +"')," +
                "(927, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.2Different Forms of Straight Line', 'Conversion of General Form into Different Form', '12', '"+ getVideoUri("video") +"')," +
                "(928, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.3 Perpendicular Distance,Foot,Image', 'Perpendicular Distance of a Point,Foot,Image', '10', '"+ getVideoUri("video") +"')," +
                "(929, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.3 Perpendicular Distance,Foot,Image', 'Position of a Point w.r.t. the Line', '10', '"+ getVideoUri("video") +"')," +
                "(930, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.3 Perpendicular Distance,Foot,Image', 'Distance between Two Lines,Some Special Points', '12', '"+ getVideoUri("video") +"')," +
                "(931, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.4 Family of Lines,Angle Bisector', 'Family of Lines', '10', '"+ getVideoUri("video") +"')," +
                "(932, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.4 Family of Lines,Angle Bisector', 'Angle Bisector', '10', '"+ getVideoUri("video") +"')," +
                "(933, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.4 Family of Lines,Angle Bisector', 'Examples,Concurrent Lines', '15', '"+ getVideoUri("video") +"')," +
                "(934, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.4 Family of Lines,Angle Bisector', 'Transformation of Axes', '20', '"+ getVideoUri("video") +"')," +
                "(935, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.5 Pair of Straight Lines', 'Pair of Lines Passing through Origin', '20', '"+ getVideoUri("video") +"')," +
                "(936, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.5 Pair of Straight Lines', 'Point of Intersection and Angle Bisectors', '12', '"+ getVideoUri("video") +"')," +
                "(937, 'Class 11', 'Maths', '7. STRAIGHT LINES', '7.5 Pair of Straight Lines', 'Equation of Lines Joining the Origin with Intersection of a Curve', '10', '"+ getVideoUri("video") +"')," +
                "(938, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.1 Different Forms of Circle', 'Different Forms of Circle', '10', '"+ getVideoUri("video") +"')," +
                "(939, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.1 Different Forms of Circle', 'Examples', '15', '"+ getVideoUri("video") +"')," +
                "(940, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.1 Different Forms of Circle', 'Point and Circle,Length of Intercept', '10', '"+ getVideoUri("video") +"')," +
                "(941, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.2 Equation of Tangent and Normal', 'Condition for Tangent', '10', '"+ getVideoUri("video") +"')," +
                "(942, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.2 Equation of Tangent and Normal', 'Angle between Two Tangents', '10', '"+ getVideoUri("video") +"')," +
                "(943, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.2 Equation of Tangent and Normal', 'Equation of Tangent and Normal at a Point', '10', '"+ getVideoUri("video") +"')," +
                "(944, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.3 Equation of Chord,Polar,Family of Circles', 'Equation of Chord of Contact,Chord with Given Mid-Point', '15', '"+ getVideoUri("video") +"')," +
                "(945, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.3 Equation of Chord,Polar,Family of Circles', 'Examples', '15', '"+ getVideoUri("video") +"')," +
                "(946, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.3 Equation of Chord,Polar,Family of Circles', 'Combined Equation of Tangents,Polar,Family of Circles', '10', '"+ getVideoUri("video") +"')," +
                "(947, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.4 Common Tangents,Family of Circles', 'Common Tangents,Orthogonal Intersection', '10', '"+ getVideoUri("video") +"')," +
                "(948, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.4 Common Tangents,Family of Circles', 'Family of Circles,Radical Axis', '12', '"+ getVideoUri("video") +"')," +
                "(949, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.5 Examples on Circle,Parabola', 'Miscellaneous Examples on Circle', '15', '"+ getVideoUri("video") +"')," +
                "(950, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.5 Examples on Circle,Parabola', 'Parabola', '12', '"+ getVideoUri("video") +"')," +
                "(951, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.5 Examples on Circle,Parabola', 'Parametric Form', '10', '"+ getVideoUri("video") +"')," +
                "(952, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.6 Parabola', 'Equation of Tangent', '12', '"+ getVideoUri("video") +"')," +
                "(953, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.6 Parabola', 'Normal', '10', '"+ getVideoUri("video") +"')," +
                "(954, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.6 Parabola', 'Point of Intersection of Normals', '10', '"+ getVideoUri("video") +"')," +
                "(955, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.7 Equation of Chord,Polor,Tangents', 'Equation of Chord,Polar,Tangents', '12', '"+ getVideoUri("video") +"')," +
                "(956, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.7 Equation of Chord,Polor,Tangents', 'Diameter', '15', '"+ getVideoUri("video") +"')," +
                "(957, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.8 Ellipse-Standard Equation', 'Ellipse-Definition and Equation', '10', '"+ getVideoUri("video") +"')," +
                "(958, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.8 Ellipse-Standard Equation', 'Standard Equation', '12', '"+ getVideoUri("video") +"')," +
                "(959, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.8 Ellipse-Standard Equation', 'Point and Ellipse,Condition for Tangent', '10', '"+ getVideoUri("video") +"')," +
                "(960, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.9 Ellipse-Tangent,Normal,Chord', 'Equation of Tangent,Chord,Polar', '10', '"+ getVideoUri("video") +"')," +
                "(961, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.9 Ellipse-Tangent,Normal,Chord', 'Equation of Normal', '12', '"+ getVideoUri("video") +"')," +
                "(962, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.10 Diameter of Ellipse,Hyperbola', 'Diameter and Conjugate', '12', '"+ getVideoUri("video") +"')," +
                "(963, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.10 Diameter of Ellipse,Hyperbola', 'Hyperbola-Standard Equation', '15', '"+ getVideoUri("video") +"')," +
                "(964, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.11 Tangent,Normal of Hyperbola,Rectangular Hyperbola', 'Equation of Tangent,Chord', '10', '"+ getVideoUri("video") +"')," +
                "(965, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.11 Tangent,Normal of Hyperbola,Rectangular Hyperbola', 'Equation of Normal', '5', '"+ getVideoUri("video") +"')," +
                "(966, 'Class 11', 'Maths', '8. CONIC SECTIONS', '8.11 Tangent,Normal of Hyperbola,Rectangular Hyperbola', 'Rectangular Hyperbola', '5', '"+ getVideoUri("video") +"')," +
                "(967, 'Class 11', 'Maths', '9. MATHEMATICAL REASONING', '9.1 Mathematical Reasoning', 'Statement Negation,AND', '10', '"+ getVideoUri("video") +"')," +
                "(968, 'Class 11', 'Maths', '9. MATHEMATICAL REASONING', '9.1 Mathematical Reasoning', 'Connecting Word OR', '15', '"+ getVideoUri("video") +"')," +
                "(969, 'Class 11', 'Maths', '9. MATHEMATICAL REASONING', '9.1 Mathematical Reasoning', 'If Then Statement', '15', '"+ getVideoUri("video") +"')," +
                "(970, 'Class 11', 'Maths', '9. MATHEMATICAL REASONING', '9.1 Mathematical Reasoning', 'If and Only if Statement,Validating Statement', '15', '"+ getVideoUri("video") +"')," +
                "(971, 'Class 11', 'Maths', '9. MATHEMATICAL REASONING', '9.1 Mathematical Reasoning', 'Examples,Truth Table', '5', '"+ getVideoUri("video") +"')," +
                "(972, 'Class 11', 'Maths', '10. SETS', '10.1 Sets', 'Definition,Types,Subset', '10', '"+ getVideoUri("video") +"')," +
                "(973, 'Class 11', 'Maths', '10. SETS', '10.1 Sets', 'Proper subset definition', '10', '"+ getVideoUri("video") +"')," +
                "(974, 'Class 11', 'Maths', '10. SETS', '10.1 Sets', 'Properties of zero operations union,Intersection', '10', '"+ getVideoUri("video") +"')," +
                "(975, 'Class 11', 'Maths', '10. SETS', '10.1 Sets', 'n(A union B) and n(A union B union c) formula', '10', '"+ getVideoUri("video") +"')," +
                "(976, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.1 Relation', 'Domain,Range of Relation', '15', '"+ getVideoUri("video") +"')," +
                "(977, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.1 Relation', 'Order pair,Cartesian product', '15', '"+ getVideoUri("video") +"')," +
                "(978, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.1 Relation', 'Function-Definition', '20', '"+ getVideoUri("video") +"')," +
                "(979, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.2 Function', 'Intervals,Inequalities', '10', '"+ getVideoUri("video") +"')," +
                "(980, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.2 Function', 'Domain', '10', '"+ getVideoUri("video") +"')," +
                "(981, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.2 Function', 'Modulus Function', '15', '"+ getVideoUri("video") +"')," +
                "(982, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.3 Types of Function', 'Greatest Integer Function', '10', '"+ getVideoUri("video") +"')," +
                "(983, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.3 Types of Function', 'Signum Exponential Function', '10', '"+ getVideoUri("video") +"')," +
                "(984, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.3 Types of Function', 'Log Function', '12', '"+ getVideoUri("video") +"')," +
                "(985, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.4 Domain,Range', 'Examples of Domain', '10', '"+ getVideoUri("video") +"')," +
                "(986, 'Class 11', 'Maths', '11. RELATIONS AND FUNCTIONS', '11.4 Domain,Range', 'Range', '10', '"+ getVideoUri("video") +"')," +
                "(987, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.1 Limit-Methods to Find Limit', 'Limit,Definition', '15', '"+ getVideoUri("video") +"')," +
                "(988, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.1 Limit-Methods to Find Limit', 'Methods to find Limit,Standard Results', '14', '"+ getVideoUri("video") +"')," +
                "(989, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.1 Limit-Methods to Find Limit', 'Examples on Limit', '12', '"+ getVideoUri("video") +"')," +
                "(990, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.2 Derivatives', 'Differentiation-Definition Standard', '12', '"+ getVideoUri("video") +"')," +
                "(991, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.2 Derivatives', 'Product,Quotient Rule', '12', '"+ getVideoUri("video") +"')," +
                "(992, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.2 Derivatives', 'Chain Rule', '10', '"+ getVideoUri("video") +"')," +
                "(993, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.3 LHospital Rule and Examples', 'LHospital Rule', '15', '"+ getVideoUri("video") +"')," +
                "(994, 'Class 11', 'Maths', '12. LIMITS & DERIVATIVES', '12.3 LHospital Rule and Examples', 'Examples', '15', '"+ getVideoUri("video") +"')," +
                "(995, 'Class 11', 'Maths', '13. INTRODUCTION TO THREE-DIMENSIONAL GEOMETRIES', '13.1 Three Dimensional Geometries', 'Introduction', '10', '"+ getVideoUri("video") +"')," +
                "(996, 'Class 11', 'Maths', '13. INTRODUCTION TO THREE-DIMENSIONAL GEOMETRIES', '13.1 Three Dimensional Geometries', 'Distance between Points', '12', '"+ getVideoUri("video") +"')," +
                "(997, 'Class 11', 'Maths', '13. INTRODUCTION TO THREE-DIMENSIONAL GEOMETRIES', '13.1 Three Dimensional Geometries', 'Section Formulae', '15', '"+ getVideoUri("video") +"')," +
                "(998, 'Class 11', 'Maths', '13. INTRODUCTION TO THREE-DIMENSIONAL GEOMETRIES', '13.1 Three Dimensional Geometries', 'Application of Section Formula', '12', '"+ getVideoUri("video") +"')," +
                "(999, 'Class 11', 'Maths', '14. STATISTICS', '14.1 Statistics', 'Mean Deviation for Ungrouped Data', '10', '"+ getVideoUri("video") +"')," +
                "(1000, 'Class 11', 'Maths', '14. STATISTICS', '14.1 Statistics', 'Mean Deviation for grouped Data', '10', '"+ getVideoUri("video") +"')," +
                "(1001, 'Class 11', 'Maths', '14. STATISTICS', '14.1 Statistics', 'Variance and Standard Deviation', '10', '"+ getVideoUri("video") +"')," +
                "(1002, 'Class 11', 'Maths', '15. PROBABILITY', '15.1 Classical Definition of Probability', 'Basic Term', '10', '"+ getVideoUri("video") +"')," +
                "(1003, 'Class 11', 'Maths', '15. PROBABILITY', '15.1 Classical Definition of Probability', 'Classical Definition', '15', '"+ getVideoUri("video") +"')," +
                "(1004, 'Class 11', 'Maths', '15. PROBABILITY', '15.1 Classical Definition of Probability', 'Probability on Playing Cards', '10', '"+ getVideoUri("video") +"')," +
                "(1005, 'Class 11', 'Maths', '15. PROBABILITY', '15.1 Classical Definition of Probability', 'Probability on Solution and Arrangement', '10', '"+ getVideoUri("video") +"')," +
                "(1006, 'Class 11', 'Maths', '15. PROBABILITY', '15.2 Axiomatic Approach of Probability', 'Algebra of Events', '12', '"+ getVideoUri("video") +"')," +
                "(1007, 'Class 11', 'Maths', '15. PROBABILITY', '15.2 Axiomatic Approach of Probability', 'Axiomatic Approach', '12', '"+ getVideoUri("video") +"')," +
                "(1008, 'Class 11', 'Maths', '15. PROBABILITY', '15.2 Axiomatic Approach of Probability', 'Mutually Exclusive and Exhaustive Events', '15', '"+ getVideoUri("video") +"')," +
                "(1009, 'Class 11', 'Maths', '16. LINEAR INEQUALITIES', '16.1 Linear Inequalities', 'Introduction', '10', '"+ getVideoUri("video") +"')," +
                "(1010, 'Class 11', 'Maths', '16. LINEAR INEQUALITIES', '16.1 Linear Inequalities', 'Critical Point Method', '10', '"+ getVideoUri("video") +"')," +
                "(1011, 'Class 11', 'Maths', '16. LINEAR INEQUALITIES', '16.1 Linear Inequalities', 'Graphical Solution', '10', '"+ getVideoUri("video") +"');";

        db.execSQL(sql3);

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
