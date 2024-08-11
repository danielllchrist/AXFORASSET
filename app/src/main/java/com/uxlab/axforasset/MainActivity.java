package com.uxlab.axforasset;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private User user = new User("user", "user@example.com");

    private ArrayList<Asset> assets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User("", "");

        ArrayList<Asset> assets = new ArrayList<>();

        assets.add(new Asset(
                "Knife",
                "Knife is a melee weapon that allows for quick, silent takedowns and can be used to increase movement speed when equipped.",
                "Knife is a melee weapon that allows for quick, silent takedowns and can be used to increase movement speed when equipped. It has a standard and an alternate fire mode, the latter dealing more damage but having a slower swing rate. It's a crucial tool for stealthy players and can be a last-resort weapon in close quarters.",
                R.drawable.knife
        ));

        assets.add(new Asset(
                "Pistols",
                "Pistol versatile secondary weapons that range from the classic semi-automatic sidearm to the powerful Desert Eagle-like Sheriff.",
                "Pistol is versatile secondary weapons that range from the classic semi-automatic sidearm to the powerful Desert Eagle-like Sheriff. They are often used in eco rounds or as a backup when primary ammo is depleted, offering a balance of accuracy, fire rate, and damage depending on the model.",
                R.drawable.pistols
        ));

        assets.add(new Asset(
                "Vandal",
                "Vandal is a high-powered assault rifle that is lethal at any range, known for its consistent damage output and one-tap headshot capability.",
                "Vandal is a high-powered assault rifle that is lethal at any range, known for its consistent damage output and one-tap headshot capability. It offers high accuracy with manageable recoil, making it a go-to weapon for players who rely on precision and firepower.",
                R.drawable.vandal
        ));

        assets.add(new Asset(
                "Phantom",
                "Phantom is a fully automatic assault rifle known for its precision and versatility, excelling at both short and mid-range engagements.",
                "Phantom is a fully automatic assault rifle known for its precision and versatility, excelling at both short and mid-range engagements. It has a suppressor which helps in keeping the shots quieter and reducing recoil, making it a favored choice for players who prefer a stealthier approach.",
                R.drawable.phantom
        ));

        assets.add(new Asset(
                "Judge",
                "Judge is a shotgun that excels in close-quarters combat, known for its high fire rate and burst damage potential. \n ",
                "Judge is a shotgun that excels in close-quarters combat, known for its high fire rate and burst damage potential. It has a wide spread and can eliminate enemies with a single shot at close range, making it a popular choice for aggressive players.",
                R.drawable.judge
        ));

        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.putExtra("user", user);
        intent.putExtra("assets", assets);
        startActivity(intent);
    }
}
