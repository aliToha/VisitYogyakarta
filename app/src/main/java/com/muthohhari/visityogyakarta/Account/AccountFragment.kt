package com.muthohhari.visityogyakarta.Account


import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.muthohhari.visityogyakarta.R
import com.muthohhari.visityogyakarta.login.Login
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class AccountFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var btnSignout: Button
    private lateinit var emailAccount: TextView
    private lateinit var userAccount: TextView
    private lateinit var imageAccount: ImageView
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.alpha(R.color.splash)
                lparams(matchParent, matchParent) {
                    padding = dip(80)
                    gravity = Gravity.CENTER
                }
                imageAccount = imageView(R.drawable.ic_account) {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }.lparams(200, 200) {
                    gravity = Gravity.CENTER
                    margin = dip(resources.getDimension(R.dimen.activity_horizontal_margin))
                }
                userAccount = textView {
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.dark_grey)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.CENTER
                    padding = dip(resources.getDimension(R.dimen.padding_medium))
                }
                emailAccount = textView {
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.dark_grey)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.CENTER
                    padding = dip(resources.getDimension(R.dimen.padding_medium))
                }
                btnSignout = button {
                    text = resources.getString(R.string.button_sign_out)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                    margin = dip(resources.getDimension(R.dimen.activity_horizontal_margin))
                }
            }

        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context)
        val image = googleSignInAccount!!.photoUrl.toString()
        if (!image.isEmpty()) {
            Glide.with(view!!.context).load(image).into(imageAccount)
        }
        googleSignInClient = GoogleSignIn.getClient(ctx, gso)

        mAuth = FirebaseAuth.getInstance()

        emailAccount.text = mAuth.currentUser!!.email
        userAccount.text = mAuth.currentUser!!.displayName

        btnSignout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                googleSignInClient.signOut()
                FirebaseAuth.getInstance().signOut()
                startActivity<Login>()
                activity!!.finish()
            }
        })
    }
}
