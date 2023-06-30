package heefe.example.hefelayout;

import android.content.Intent;

public class secondActivity {

    Intent gotoSecond;

    public secondActivity() {
        gotoSecond = new Intent(this,MainActivity.class);
    }
}

