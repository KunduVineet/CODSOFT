

package www.spikeysanju.jetquotes.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import www.spikeysanju.jetquotes.R
import www.spikeysanju.jetquotes.model.Quote
import www.spikeysanju.jetquotes.utils.copyToClipboard
import www.spikeysanju.jetquotes.utils.shareToOthers
import www.spikeysanju.jetquotes.view.viewModel.MainViewModel

@Composable
fun CTAButtons(viewModel: MainViewModel, quote: String, author: String) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .align(Alignment.BottomEnd)
                .padding(30.dp, 30.dp, 0.dp, 30.dp)
        ) {


            Button(
                icon = painterResource(id = R.drawable.ic_heart),
                name = stringResource(R.string.text_favourite),
                modifier = Modifier.clickable(onClick = {
                    val quotes = Quote(quote, author)
                    viewModel.insertFavourite(quotes)
                    Toast.makeText(context, "Added to Favourites!", Toast.LENGTH_SHORT).show()
                })
            )

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                icon = painterResource(id = R.drawable.ic_copy),
                name = stringResource(R.string.text_copy),
                modifier = Modifier.clickable(onClick = {
                    context.copyToClipboard(quote.plus("").plus("- $author"))
                    Toast.makeText(context, "Quote Copied!", Toast.LENGTH_SHORT).show()
                })
            )

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                icon = painterResource(id = R.drawable.ic_share),
                name = stringResource(R.string.text_share),
                modifier = Modifier.clickable(onClick = {
                    context.shareToOthers(quote.plus("").plus("- $author"))
                })
            )

            Spacer(modifier = Modifier.width(30.dp))


        }
    }


}