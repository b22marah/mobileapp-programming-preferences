# 1: Strukturering av activity_main.xml:

Förutom de widgets som medföljer vid startat program i activity_main.xml 
så skapade jag en TextView och en Button. Anledningen till att min TextView 
har inkluderats i min activity_main är för att jag behöver ett ställe att presentera 
mitt senaste lagrade värde. Knappen däremot används för att antingen skapa en ny 
preference, eller att skapa en ny, ifall ingen tidigare har skapats.

### Kod för min TextView samt Button (1);
```
<TextView
    android:id="@+id/prefText"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/app_name"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/appBarLayout"
/>

<Button
    android:id="@+id/openPreferences"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:onClick="openSecondActivity"
    android:text="Add Preferences"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/prefText"
/>
```

# 2: Strukturering / skapning av activity_second.xml:

Här skapade jag min nästa aktivitet nämligen 'activity_second.xml', denna aktivitet 
är den som kommer spara data till SharedPreferences (genom dens klass (SecondActivity)). 
Här har jag skapat en 'EditText' och 'Button', anledningen till att EditText inkluderas här 
är för att skapa en "placeholder" för användaren, där hen kan mata in valfritt värde, som 
i sin tur kommer att sparas i SharedPreferences, min button i activity_second används 
däremot för att avsluta aktiviteten, spara SharedPreferences samt byta tillbaka till activity_main fönstret.

### Kod för min EditView samt Button (2);
```
<EditText
    android:id="@+id/edit_Text"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter here: "
/>

<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Save"
    android:onClick="savePref"
/>
```

# 3: Skapandet av Intents:

För denna uppgiften har jag två stycken knappar, varsin i respektive aktivitet. 
Dessa knappar använder jag i programmet för att skifta mellan de två olika aktiviteterna, 
så det jag har gjort, är att skapa en Intent för knappen på min 'activity_main'. 
Det denna Intent gör är att den kallar på en funktion som startar en aktivitet vid knapp-tryck, 
till detta skapades en variabel 'Intent' av typen 'Intent', som "pekar" på aktivitet två, alltså 
kommer andra aktiviteten starta när knappen från första aktiviteten klickas. 
Därefter kan användaren skapa sin nya SharedPreferences och när den är klar sparas detta via knapp-tryck 
på knappen i andra aktiviteten. Istället för att skapa / korrigera min första Intent till att skifta aktivitet på nytt, 
så har jaag instället ett funktions-kall vid knapptryckning som stänger ner den aktuella aktiviteten.

### MainActivity.Java (3):
```
public void openSecondActivity(View view) {
   Intent intent = new Intent(this, SecondActivity.class);
   startActivity(intent);
}
```

### SecondActivity.Java (3):
```
protected void onPause() {
    super.onPause();

    EditText editText = findViewById(R.id.edit_Text);
    String newPreference = editText.getText().toString();

    myPreferenceEditor.putString("MyappPreferenceString", newPreference);
    myPreferenceEditor.apply();

    finish();
}
```


# 4: Lagra nytt värde från EditText i SharedPreferences:

I 'SecondActivity.java', i 'onCreate()'-metoden hämtas det först in all data 
till SharedPreferences och SharedPreferences.Editor, detta görs för att kunna hämta och eventuellt
spara ny data från SharedPreferences. 

### onCreate:
```
public class SecondActivity extends AppCompatActivity {

    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get a reference to the shared preference
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        myPreferenceRef = getSharedPreferences("MyPreferenceName", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();
    }
    ...
}
```

Därefter har vi 'savedPref()'-metoden, som först refererar till EditText-elementet genom att söka på dess id, 
som i detta fall är "edit_Text". Därefter kommer det nya värdet (som användaren skriver in) 
att lagras i SharedPrefereces genom att använda "myPreferenceEditor.putString("MyappPreferenceString",newPreference)

som i sin tur anropar "myPreferenceEditor.apply()", som kommer att spara dem aktuella ändringarna. Efter detta
avslutas aktiviteten för att återgå till 'activity_main.xml' som jag beskrivit i tidigare del.

### savePref:
```
public void savePref(View view) {
    EditText editText = findViewById(R.id.edit_Text);
    String newPreference = editText.getText().toString();

    myPreferenceEditor.putString("MyappPreferenceString", newPreference);
    myPreferenceEditor.apply();

    finish();
}
```

Till sist har vi 'onPaus()'-metoden, som fungerar precis likadant som 'savedPref()', som jag nyligen 
beskrivit, enda skillnaden är att 'onPaus()' sparar värdet ifall / när aktiviteten pausas.

### onPause:
```
@Override
protected void onPause() {
    super.onPause();

    EditText editText = findViewById(R.id.edit_Text);
    String newPreference = editText.getText().toString();

    myPreferenceEditor.putString("MyappPreferenceString", newPreference);
    myPreferenceEditor.apply();

    finish();
}
```

# 5: Återhämtning av lagrat värde:



### 
```

```

## Bilder på färdig produkt:
